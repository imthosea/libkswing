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
import java.awt.Font
import java.text.AttributedCharacterIterator.Attribute

/**
 * Equivalent to `font = font.deriveFont(<size> as Float)`
 * @see [java.awt.Container.font]
 */
inline var Component.kFontSize: Float
	get() = font.size.toFloat()
	set(value) {
		font = font.deriveFont(value)
	}

/**
 * Equivalent to `font = font.deriveFont(<style with bold> as Int)`
 * @see [java.awt.Container.font]
 */
inline var Component.kBold: Boolean
	get() = font.isBold
	set(value) {
		val style = if(value) {
			font.style or Font.BOLD
		} else {
			font.style and Font.BOLD.inv()
		}
		font = font.deriveFont(style)
	}

/**
 * Equivalent to `font = font.deriveFont(<style with bold> as Int)`
 * @see [java.awt.Container.font]
 */
inline var Component.kItalic: Boolean
	get() = font.isBold
	set(value) {
		val style = if(value) {
			font.style or Font.ITALIC
		} else {
			font.style and Font.ITALIC.inv()
		}
		font = font.deriveFont(style)
	}

/**
 * Equivalent to `font = new Font(<name>, font.size, name.style)`
 * @see [java.awt.Container.font]
 */
inline var Component.kFontName: String
	get() = font.name
	set(value) {
		font = Font(value, font.style, font.size)
	}

/**
 * Equivalent to `font = font.deriveFont(<size> as Float)`
 * @see [java.awt.Container.font]
 */
fun <T: Component> T.kFontSize(value: Float): T {
	kFontSize = value
	return this
}

/**
 * Equivalent to `font = font.deriveFont(<style with bold> as Int)`
 * @see [java.awt.Container.font]
 */
fun <T: Component> T.kBold(value: Boolean): T {
	kBold = value
	return this
}

/**
 * Equivalent to `font = font.deriveFont(<style with bold> as Int)`
 * @see [java.awt.Container.font]
 */
fun <T: Component> T.kItalic(value: Boolean): T {
	kItalic = value
	return this
}

/**
 * Equivalent to `font = new Font(<name>, font.size, name.style)`
 * @see [java.awt.Container.font]
 */
fun <T: Component> T.kFontName(value: String): T {
	kFontName = value
	return this
}

/**
 * Equivalent to `font = font.deriveFont(<map>)`
 * @see [java.awt.Container.font]
 */
fun <T: Component> T.kFontAttr(vararg pairs: Pair<Attribute, Any?>): T {
	font = font.deriveFont(mapOf(*pairs))
	return this
}