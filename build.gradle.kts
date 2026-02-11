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
import net.fabricmc.loom.task.RunGameTask

plugins {
    alias(libs.plugins.fabric.loom)
}

// Language.
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

// Metadata.
group = "ru.vidtu.optophobic"
base.archivesName = "Optophobic"
version = "${version}+1.21.11-fabric"
description = "Remove \"Light updates disabled\" added by Sodium Extra."

loom {
    // Prepare development environment.
    log4jConfigs.setFrom(rootDir.resolve("dev/log4j2.xml"))

    // Set up runs.
    runs {
        // Customize the client run.
        named("client") {
            // Set up debug VM args.
            vmArgs("@../dev/args.vm.txt")
        }

        // Remove server run, the mod is client-only.
        remove(findByName("server"))
    }

    // Configure Mixin.
    @Suppress("UnstableApiUsage") // <- Required to configure Mixin.
    mixin {
        // Use direct remapping instead of annotation processor and refmaps.
        useLegacyMixinAp = false
    }
}

// Make the game run with the compatible Java.
tasks.withType<RunGameTask> {
    javaLauncher = javaToolchains.launcherFor(java.toolchain)
}

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/") // Fabric.
    maven("https://maven.caffeinemc.net/releases/") // Sodium.
    maven("https://api.modrinth.com/maven/") // Sodium Extra.
}

dependencies {
    // Annotations.
    compileOnly(libs.jspecify)
    compileOnly(libs.jetbrains.annotations)
    compileOnly(libs.error.prone.annotations)

    // Minecraft.
    minecraft(libs.minecraft)
    mappings(loom.officialMojangMappings())
    modImplementation(libs.fabric.loader)

    // Sodium.
    modImplementation(libs.sodium)
    modImplementation(libs.sodium.extra)
}

// Compile with UTF-8, Java 8, and with all debug options.
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.addAll(listOf("-g", "-parameters"))
    options.release = 21
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

// Add LICENSE and manifest into the JAR file.
tasks.withType<Jar> {
    // Add LICENSE.
    from("LICENSE")

    // Remove package-info.class, unless package debug is on. (to save space)
    if (!"${findProperty("ru.vidtu.optophobic.debug.package")}".toBoolean()) {
        exclude("**/package-info.class")
    }

    // Add manifest.
    manifest {
        attributes(
            "Specification-Title" to "Optophobic",
            "Specification-Version" to version,
            "Specification-Vendor" to "VidTu",
            "Implementation-Title" to "Optophobic",
            "Implementation-Version" to version,
            "Implementation-Vendor" to "VidTu"
        )
    }
}
