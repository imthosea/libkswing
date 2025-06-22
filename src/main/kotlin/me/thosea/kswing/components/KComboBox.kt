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

import java.awt.Component
import java.awt.Container
import java.util.*
import javax.swing.ComboBoxModel
import javax.swing.DefaultComboBoxModel
import javax.swing.JComboBox
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * [JComboBox] and adds it to the [Container] you call it on
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html)
 */
inline fun <T> Container.KComboBox(
	model: ComboBoxModel<T> = DefaultComboBoxModel<T>(),
	addConstraints: Any? = null, addIndex: Int = -1, setup: JComboBox<T>.() -> Unit = {},
): JComboBox<T> {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val box = JComboBox(model).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(box)
	add(box, addConstraints, addIndex)
	return box
}

/**
 * [JComboBox]
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html)
 */
inline fun <T> KLazyComboBox(
	model: ComboBoxModel<T> = DefaultComboBoxModel<T>(),
	setup: JComboBox<T>.() -> Unit = {},
): JComboBox<T> {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val box = JComboBox(model).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(box)
	return box
}

/**
 * [JComboBox] and adds it to the [Container] you call it on
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html)
 */
inline fun <T> Container.KComboBox(
	array: Array<T>,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JComboBox<T>.() -> Unit = {},
): JComboBox<T> {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val box = JComboBox(array).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(box)
	add(box, addConstraints, addIndex)
	return box
}

/**
 * [JComboBox]
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html)
 */
inline fun <T> KComboBox(
	array: Array<T>,
	setup: JComboBox<T>.() -> Unit = {},
): JComboBox<T> {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val box = JComboBox(array).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(box)
	return box
}

/**
 * [JComboBox] and adds it to the [Container] you call it on

 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html)
 */
inline fun <T> Container.KComboBox(
	vector: Vector<T>,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JComboBox<T>.() -> Unit = {},
): JComboBox<T> {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val box = JComboBox(vector).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(box)
	add(box, addConstraints, addIndex)
	return box
}

/**
 * [JComboBox]

 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html)
 */
inline fun <T> KLazyComboBox(
	vector: Vector<T>,
	setup: JComboBox<T>.() -> Unit = {},
): JComboBox<T> {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val box = JComboBox(vector).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(box)
	return box
}