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

@file:OptIn(ExperimentalContracts::class)
@file:Suppress("FunctionName", "Unused")

package me.thosea.kswing.components

import java.awt.Component
import java.awt.Container
import javax.swing.Icon
import javax.swing.JLabel
import javax.swing.SwingConstants
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * [JLabel] and adds it to the [Container] you call it on.
 * If you add [HtmlDecorations], your text will be HTML-escaped.
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/label.html)
 */
inline fun Container.KLabel(
	html: HtmlDecorations = HtmlDecorations.NONE, text: String,
	icon: Icon? = null,
	horizontalAlignment: Int = SwingConstants.CENTER,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JLabel.() -> Unit = {},
): JLabel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val label = JLabel(html.decorate(text), icon, horizontalAlignment).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(label)
	add(label, addConstraints, addIndex)
	return label
}

/**
 * [JLabel].
 * If you add [HtmlDecorations], your text will be HTML-escaped.
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/label.html)
 */
inline fun KLazyLabel(
	html: HtmlDecorations = HtmlDecorations.NONE, text: String,
	icon: Icon? = null,
	horizontalAlignment: Int = SwingConstants.CENTER,
	setup: JLabel.() -> Unit = {},
): JLabel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val label = JLabel(html.decorate(text), icon, horizontalAlignment).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(label)
	return label
}

/**
 * [JLabel] and adds it to the [Container] you call it on
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/label.html)
 */
// overload so you don't have to type "text = "
inline fun Container.KLabel(
	text: String = "", icon: Icon? = null,
	horizontalAlignment: Int = SwingConstants.CENTER,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JLabel.() -> Unit = {},
): JLabel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val label = JLabel(text, icon, horizontalAlignment).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(label)
	add(label, addConstraints, addIndex)
	return label
}

/**
 * [JLabel]
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/label.html)
 */
inline fun KLazyLabel(
	text: String = "", icon: Icon? = null,
	horizontalAlignment: Int = SwingConstants.CENTER,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JLabel.() -> Unit = {},
): JLabel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val label = JLabel(text, icon, horizontalAlignment).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(label)
	return label
}

enum class HtmlDecorations(private val prefix: String?, private val suffix: String?) {
	NONE(null, null),
	BASIC("<html>", "</html>"),
	DIV_CENTER("<html><div style='text-align: center''>", "</div></html>"),
	DIV_RIGHT("<html><div style='text-align: right'>", "</div></html>"),
	DIV_LEFT("<html><div style='text-align: left'>", "</div></html>"),
	DIV_JUSTIFY("<html><div style='text-align: justify'>", "</div></html>");

	fun decorate(text: String): String {
		if(prefix == null || suffix == null) return text

		val builder = StringBuilder(prefix.length + text.length + suffix.length)
		builder.append(prefix)
		for(c in text.toCharArray()) {
			when(c) {
				'<' -> builder.append("&lt;")
				'>' -> builder.append("&gt;")
				'&' -> builder.append("&amp;")
				'"' -> builder.append("&quot;")
				'\'' -> builder.append("&#39;")
				else -> builder.append(c)
			}
		}
		builder.append(suffix)
		return builder.toString()
	}
}