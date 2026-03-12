/*
 * Optophobic is a third-party mod for Minecraft Java Edition
 * that removes "Light updates disabled" added by Sodium Extra.
 *
 * MIT License
 *
 * Copyright (c) 2026 VidTu
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * SPDX-License-Identifier: MIT
 */

import com.google.gson.Gson
import com.google.gson.JsonElement

plugins {
    id("java")
}

// Language.
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    toolchain.languageVersion = JavaLanguageVersion.of(8)
}

// Metadata.
group = "ru.vidtu.optophobic"
base.archivesName = "Optophobic"
description = "Remove \"Light updates disabled\" added by Sodium Extra."

// Add GSON to buildscript classpath, we use it for minifying JSON files.
buildscript {
    dependencies {
        classpath(libs.gson)
    }
}

repositories {
    mavenCentral()
    maven("https://repo.spongepowered.org/repository/maven-public/") // Mixin.
}

dependencies {
    // Annotations.
    compileOnly(libs.jspecify)
    compileOnly(libs.jetbrains.annotations)
    compileOnly(libs.error.prone.annotations)

    // Minecraft.
    compileOnly(libs.mixin)
    compileOnly(libs.asm) // Required for Mixin.
}

// Compile with UTF-8, Java 8, and with all debug options.
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.addAll(listOf("-g", "-parameters"))
    // JDK 8 (used by this project) doesn't support the "-release" flag and
    // uses "-source" and "-target" ones (see the top of the file),
    // so we must NOT specify it, or the "javac" will fail.
    // If we ever gonna compile on newer Java versions, uncomment this line.
    // options.release = 8
}

tasks.withType<ProcessResources> {
    // Filter with UTF-8.
    filteringCharset = "UTF-8"

    // Expand version.
    inputs.property("version", version)
    filesMatching(listOf("fabric.mod.json")) {
        expand(inputs.properties)
    }

    // Minify JSON.
    var files = fileTree(outputs.files.asPath)
    doLast {
        files.forEach {
            if (it.name.endsWith(".json", ignoreCase = true)) {
                it.writeText(Gson().fromJson(it.readText(), JsonElement::class.java).toString())
            }
        }
    }
}

tasks.withType<Jar> {
    // Add LICENSE into the JAR file.
    from("LICENSE")

    // Exclude mock classes.
    exclude("me/**")

    // Remove package-info.class, unless package debug is on. (to save space)
    if (!"${findProperty("ru.vidtu.optophobic.debug.package")}".toBoolean()) {
        exclude("**/package-info.class")
    }
}
