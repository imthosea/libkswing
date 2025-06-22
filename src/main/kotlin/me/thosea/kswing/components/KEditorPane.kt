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
import javax.swing.JEditorPane
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

// constructors are weird here ...
// all of them just call the no-arg then setting something,
// which you could easily do in setup() so i'll just keep the no-arg

/**
 * [JEditorPane] and adds it to the [Container] you call it on
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/editorpane.html)
 */
inline fun Container.KEditorPane(
	addConstraints: Any? = null, addIndex: Int = -1, setup: JEditorPane.() -> Unit = {},
): JEditorPane {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val pane = JEditorPane().apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(pane)
	add(pane, addConstraints, addIndex)
	return pane
}

/**
 * [JEditorPane]
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/editorpane.html)
 */
inline fun KLazyEditorPane(setup: JEditorPane.() -> Unit = {}, ): JEditorPane {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val pane = JEditorPane().apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(pane)
	return pane
}