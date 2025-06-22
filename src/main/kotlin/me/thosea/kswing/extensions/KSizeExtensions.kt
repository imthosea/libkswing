/*
 * Copyright 2025 Thosea (github.com/imthosea)
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS “AS IS”
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

@file:Suppress("Unused")

package me.thosea.kswing.extensions

import java.awt.Component
import java.awt.Container
import java.awt.Dimension
import kotlin.math.min

/**
 * Changes the [Container.maximumSize] to the width you specify and [Container.getPreferredSize]'s height
 */
fun <T: Component> T.kCapWidth(width: Int): T {
	maximumSize = Dimension(width, preferredSize.height)
	capPreferredToMaximum()
	return this
}

/**
 * Changes the [Container.maximumSize] to [Container.getPreferredSize]'s width and the height you specify
 */
fun <T: Component> T.kCapHeight(height: Int): T {
	maximumSize = Dimension(preferredSize.width, height)
	capPreferredToMaximum()
	return this
}

/**
 * Changes the [Container.maximumSize] to [Container.getPreferredSize]
 */
fun <T: Component> T.kCapSizeToPreferred(): T {
	maximumSize = preferredSize
	capPreferredToMaximum()
	return this
}

/**
 * Changes [Container.maximumSize]'s width to [Container.getPreferredSize] width,
 * keeping height the same
 */
fun <T: Component> T.kCapWidthToPreferred(): T {
	maximumSize = Dimension(preferredSize.width, maximumSize.height)
	capPreferredToMaximum()
	return this
}

/**
 * Changes [Container.maximumSize]'s height to [Container.getPreferredSize] height,
 * keeping width the same
 */
fun <T: Component> T.kCapHeightToPreferred(): T {
	maximumSize = Dimension(maximumSize.width, preferredSize.height)
	capPreferredToMaximum()
	return this
}

/**
 * Changes [Container.maximumSize] to what you specify
 */
fun <T: Component> T.kCapSize(width: Int, height: Int): T {
	maximumSize = Dimension(width, height)
	capPreferredToMaximum()
	return this
}

/**
 * Changes [Container.getPreferredSize] to what you specify
 */
fun <T: Component> T.kPreferSize(width: Int, height: Int): T {
	preferredSize = Dimension(width, height)
	return this
}

private fun Component.capPreferredToMaximum() {
	val preferred = preferredSize
	val max = maximumSize
	if(preferred.width > max.width || preferred.height > max.height) {
		preferredSize = Dimension(min(preferred.width, max.width), min(preferred.height, max.height))
	}
}