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

package me.thosea.kswing.components.custom

import me.thosea.kswing.listeners.kComponentResizedListener
import me.thosea.kswing.other.FullyOpen
import java.awt.Component
import java.awt.Container
import java.awt.Graphics
import java.awt.Shape
import java.awt.geom.Ellipse2D
import javax.swing.ButtonGroup
import javax.swing.Icon
import javax.swing.JButton
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * [KCircleButtonImpl] and adds it to the [Container] you call it on
 *
 * This is a custom KSwing component.
 */
inline fun Container.KCircleButton(
	text: String? = null, icon: Icon? = null,
	buttonGroup: ButtonGroup? = null,
	addConstraints: Any? = null, addIndex: Int = -1, setup: KCircleButtonImpl.() -> Unit = {}
): KCircleButtonImpl {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val button = KCircleButtonImpl(text, icon).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	buttonGroup?.add(button)
	setup.invoke(button)
	add(button, addConstraints, addIndex)
	return button
}

/**
 * [KCircleButtonImpl]
 *
 * This is a custom KSwing component.
 */
inline fun KLazyCircleButton(
	text: String? = null, icon: Icon? = null,
	buttonGroup: ButtonGroup? = null,
	setup: KCircleButtonImpl.() -> Unit = {}
): KCircleButtonImpl {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val button = KCircleButtonImpl(text, icon).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	buttonGroup?.add(button)
	setup.invoke(button)
	return button
}

/**
 * [JButton] but now a circle.
 */
@FullyOpen
class KCircleButtonImpl(text: String? = null, icon: Icon? = null): JButton(text, icon) {
	@Suppress("ProtectedInFinal") // unaware of FullyOpen
	var shape: Shape = makeShape()
		protected set

	init {
		kComponentResizedListener {
			shape = makeShape()
		}
	}

	@Suppress("ProtectedInFinal")
	protected fun makeShape() = Ellipse2D.Double(0.0, 0.0, width.toDouble(), height.toDouble())

	override fun paintComponent(graphics: Graphics) {
		graphics.clip = shape
		super.paintComponent(graphics)
	}
}