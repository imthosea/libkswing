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

@file:Suppress("FunctionName", "Unused")
@file:OptIn(ExperimentalContracts::class)

package me.thosea.kswing.components

import java.awt.Color
import java.awt.Component
import java.awt.Container
import javax.swing.JColorChooser
import javax.swing.colorchooser.ColorSelectionModel
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * [JColorChooser] and adds it to the [Container] you call it on
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html)
 */
inline fun Container.KColorChooser(
	model: ColorSelectionModel,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JColorChooser.() -> Unit = {},
): JColorChooser {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val chooser = JColorChooser(model).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(chooser)
	add(chooser, addConstraints, addIndex)
	return chooser
}

/**
 * [JColorChooser]
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html)
 */
inline fun KLazyColorChooser(
	model: ColorSelectionModel,
	setup: JColorChooser.() -> Unit = {},
): JColorChooser {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val chooser = JColorChooser(model).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(chooser)
	return chooser
}

/**
 * [JColorChooser] and adds it to the [Container] you call it on
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html)
 */
inline fun Container.KColorChooser(
	color: Color = Color.white,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JColorChooser.() -> Unit = {},
): JColorChooser {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val chooser = JColorChooser(color).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(chooser)
	add(chooser, addConstraints, addIndex)
	return chooser
}

/**
 * [JColorChooser]
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html)
 */
inline fun KLazyColorChooser(
	color: Color = Color.white,
	setup: JColorChooser.() -> Unit = {},
): JColorChooser {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val chooser = JColorChooser(color).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(chooser)
	return chooser
}