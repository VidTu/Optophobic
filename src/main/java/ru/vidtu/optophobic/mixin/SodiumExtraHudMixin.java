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

package ru.vidtu.optophobic.mixin;

import com.google.errorprone.annotations.DoNotCall;
import me.flashyreese.mods.sodiumextra.client.config.SodiumExtraGameOptions;
import me.flashyreese.mods.sodiumextra.client.gui.SodiumExtraHud;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Mixin that does the job.
 *
 * @author VidTu
 * @apiNote Internal use only
 */
// @ApiStatus.Internal // Can't annotate this without logging in the console.
@Mixin(value = SodiumExtraHud.class, remap = false)
@NullMarked
public final class SodiumExtraHudMixin {
    /**
     * An instance of this class cannot be created.
     *
     * @throws AssertionError Always
     * @deprecated Always throws
     */
    @Deprecated(forRemoval = true)
    @Contract(value = "-> fail", pure = true)
    private SodiumExtraHudMixin() {
        throw new AssertionError("Optophobic: No instances.");
    }

    /**
     * Makes {@code lightUpdates} to be always {@code true} inside HUD handling.
     *
     * @param ignoredSettings Settings module, ignored
     * @return Always {@code true}
     * @apiNote Do not call, called by Mixin
     */
    @DoNotCall("Called by Mixin")
    @Contract(value = "_ -> true", pure = true)
    @Redirect(method = "onStartTick", at = @At(value = "FIELD", target = "Lme/flashyreese/mods/sodiumextra/client/config/SodiumExtraGameOptions$RenderSettings;lightUpdates:Z", opcode = Opcodes.GETFIELD))
    private boolean optophobic_onStartTick_lightUpdates(final SodiumExtraGameOptions.RenderSettings ignoredSettings) {
        return true;
    }
}
