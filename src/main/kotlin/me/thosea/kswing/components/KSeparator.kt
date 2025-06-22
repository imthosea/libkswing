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
import javax.swing.JSeparator
import javax.swing.SwingConstants
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * [JSeparator] and adds it to the [Container] you call it on
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/separator.html)
 */
inline fun Container.KSeparator(
	orientation: Int = SwingConstants.HORIZONTAL,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JSeparator.() -> Unit = {},
): JSeparator {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val separator = JSeparator(orientation).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(separator)
	add(separator, addConstraints, addIndex)
	return separator
}

/**
 * [JSeparator]
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/separator.html)
 */
inline fun KLazySeparator(
	orientation: Int = SwingConstants.HORIZONTAL,
	setup: JSeparator.() -> Unit = {},
): JSeparator {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val separator = JSeparator(orientation).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(separator)
	return separator
}