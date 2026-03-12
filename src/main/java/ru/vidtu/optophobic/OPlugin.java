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

package ru.vidtu.optophobic;

import com.google.errorprone.annotations.DoNotCall;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.CheckReturnValue;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.service.IClassBytecodeProvider;
import org.spongepowered.asm.service.MixinService;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Mixin plugin that silences the console {@link ClassNotFoundException} errors from Optophobic.
 *
 * @author VidTu
 * @apiNote Internal use only
 */
@ApiStatus.Internal
@NullMarked
public final class OPlugin implements IMixinConfigPlugin {
    /**
     * Current Mixin bytecode provider.
     */
    private final IClassBytecodeProvider provider = MixinService.getService().getBytecodeProvider();

    /**
     * Creates a new plugin.
     *
     * @apiNote Do not call, called by Mixin
     */
    @Contract(pure = true)
    public OPlugin() {
        // Empty.
    }

    /**
     * Checks if the mixin should be applied. A mixin is applied, if its class node exists.
     *
     * @param targetClassName Fully qualified class name of the target class
     * @param mixinClassName  Fully qualified class name of the mixin
     * @return Whether the Mixin should be applied
     * @throws RuntimeException If any unexpected exception occurs (should never be thrown)
     * @apiNote Do not call, called by Mixin
     * @see #provider
     * @see IClassBytecodeProvider#getClassNode(String)
     */
    @DoNotCall("Called by Mixin")
    @CheckReturnValue
    @Override
    public boolean shouldApplyMixin(final String targetClassName, final String mixinClassName) {
        // Wrap to handle exceptions as a control flow.
        try {
            // If the Mixin class is not from Optophobic, don't touch it and allow it to be applied.
            if (!mixinClassName.startsWith("ru.vidtu.optophobic.mixin.")) { // Implicit NPE for 'mixinClassName'
                return true;
            }

            // Get the node. This method should never return null. It returns the class node, if the class exists.
            // It throws ClassNotFoundException if the class doesn't exist. We handle the exception below.
            final ClassNode node = this.provider.getClassNode(targetClassName); // Implicit NPE for 'targetClassName'

            // Check for null.
            Objects.requireNonNull(node, "Bytecode provider returned null.");

            // Didn't throw - class exists. Apply.
            return true;
        } catch (final ClassNotFoundException cnfe) {
            // Provider threw an ClassNotFoundException. Don't apply mixin to avoid warnings.
            return false;
        } catch (final Throwable t) {
            // Throw.
            throw new RuntimeException("Optophobic: Unexpected exception. (plugin: " + this + ", targetClassName: " + targetClassName + ", mixinClassName: " + mixinClassName + ')', t);
        }
    }

    /**
     * Does nothing.
     *
     * @param mixinPackage Ignored
     * @apiNote Do not call, called by Mixin
     */
    @DoNotCall("Called by Mixin")
    @Contract(pure = true)
    @Override
    public void onLoad(final String mixinPackage) {
        // NO-OP
    }

    /**
     * Does nothing. Always returns {@code null}.
     *
     * @return Always {@code null}
     * @apiNote Do not call, called by Mixin
     */
    @DoNotCall("Called by Mixin")
    @Contract(value = "-> null", pure = true)
    @Override
    @Nullable
    public String getRefMapperConfig() {
        return null;
    }

    /**
     * Does nothing.
     *
     * @param myTargets    Ignored
     * @param otherTargets Ignored
     * @apiNote Do not call, called by Mixin
     */
    @DoNotCall("Called by Mixin")
    @Contract(pure = true)
    @Override
    public void acceptTargets(final Set<String> myTargets, final Set<String> otherTargets) {
        // NO-OP
    }

    /**
     * Does nothing. Always returns {@code null}.
     *
     * @return Always {@code null}
     * @apiNote Do not call, called by Mixin
     */
    @DoNotCall("Called by Mixin")
    @Contract(value = "-> null", pure = true)
    @Override
    @Nullable
    public List<String> getMixins() {
        return null;
    }

    /**
     * Does nothing.
     *
     * @param targetClassName Ignored
     * @param targetClass     Ignored
     * @param mixinClassName  Ignored
     * @param mixinInfo       Ignored
     * @apiNote Do not call, called by Mixin
     */
    @DoNotCall("Called by Mixin")
    @Contract(pure = true)
    @Override
    public void preApply(final String targetClassName, final ClassNode targetClass,
                         final String mixinClassName, final IMixinInfo mixinInfo) {
        // NO-OP
    }

    /**
     * Does nothing.
     *
     * @param targetClassName Ignored
     * @param targetClass     Ignored
     * @param mixinClassName  Ignored
     * @param mixinInfo       Ignored
     * @apiNote Do not call, called by Mixin
     */
    @DoNotCall("Called by Mixin")
    @Contract(pure = true)
    @Override
    public void postApply(final String targetClassName, final ClassNode targetClass,
                          final String mixinClassName, final IMixinInfo mixinInfo) {
        // NO-OP
    }

    @Contract(pure = true)
    @Override
    public String toString() {
        return "Optophobic/OPlugin{" +
                "provider=" + this.provider +
                '}';
    }
}
