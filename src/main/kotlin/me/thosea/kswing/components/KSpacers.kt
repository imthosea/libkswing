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
import java.awt.Dimension
import javax.swing.Box
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/*
 * boxes
 */

/**
 * [Box.createHorizontalBox] and adds it to the [Container] you call it on
 */
inline fun Container.KHBox(addConstraints: Any? = null, addIndex: Int = -1, setup: Box.() -> Unit = {}): Box {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val box = Box.createHorizontalBox().apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(box)
	add(box, addConstraints, addIndex)
	return box
}

/**
 * [Box.createHorizontalBox]
 */
inline fun KLazyHBox(setup: Box.() -> Unit = {}): Box {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val box = Box.createHorizontalBox().apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(box)
	return box
}

/**
 * [Box.createVerticalBox] and adds it to the [Container] you call it on
 */
inline fun Container.KVBox(addConstraints: Any? = null, addIndex: Int = -1, setup: Box.() -> Unit = {}): Box {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val box = Box.createVerticalBox().apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(box)
	add(box, addConstraints, addIndex)
	return box
}

/**
 * [Box.createVerticalBox]
 */
inline fun KLazyVBox(setup: Box.() -> Unit = {}): Box {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val box = Box.createVerticalBox().apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(box)
	return box
}

/*
 * rigid areas
 */

/**
 * [Box.createRigidArea] and adds it to the [Container] you call it on
 */
fun Container.KRigidArea(
	dimensions: Dimension,
	addConstraints: Any? = null, addIndex: Int = -1, setup: Component.() -> Unit = {},
): Component {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val area = Box.createRigidArea(dimensions)
	setup.invoke(area)
	add(area, addConstraints, addIndex)
	return area
}

/**
 * [Box.createRigidArea]
 */
fun KLazyRigidArea(
	dimensions: Dimension,
	setup: Component.() -> Unit = {},
): Component {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val area = Box.createRigidArea(dimensions)
	setup.invoke(area)
	return area
}

/**
 * [Box.createRigidArea] and adds it to the [Container] you call it on
 */
fun Container.KRigidArea(
	width: Int, height: Int,
	addConstraints: Any? = null, addIndex: Int = -1, setup: Component.() -> Unit = {},
): Component {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val area = Box.createRigidArea(Dimension(width, height))
	setup.invoke(area)
	add(area, addConstraints, addIndex)
	return area
}

/**
 * [Box.createRigidArea]
 */
fun KLazyRigidArea(
	width: Int, height: Int,
	setup: Component.() -> Unit = {},
): Component {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val area = Box.createRigidArea(Dimension(width, height))
	setup.invoke(area)
	return area
}

/*
 * structs
 */

/**
 * [Box.createHorizontalStrut] and adds it to the [Container] you call it on
 */
fun Container.KHStruct(
	width: Int,
	addConstraints: Any? = null, addIndex: Int = -1, setup: Component.() -> Unit = {},
): Component {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val area = Box.createHorizontalStrut(width)
	setup.invoke(area)
	add(area, addConstraints, addIndex)
	return area
}

/**
 * [Box.createHorizontalStrut]
 */
fun KLazyHStruct(
	width: Int,
	setup: Component.() -> Unit = {},
): Component {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val area = Box.createHorizontalStrut(width)
	setup.invoke(area)
	return area
}

/**
 * [Box.createVerticalStrut] and adds it to the [Container] you call it on
 */
fun Container.KVStruct(
	height: Int,
	addConstraints: Any? = null, addIndex: Int = -1, setup: Component.() -> Unit = {},
): Component {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val area = Box.createVerticalStrut(height)
	setup.invoke(area)
	add(area, addConstraints, addIndex)
	return area
}

/**
 * [Box.createVerticalStrut]
 */
fun KLazyVStruct(
	height: Int,
	setup: Component.() -> Unit = {},
): Component {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val area = Box.createVerticalStrut(height)
	setup.invoke(area)
	return area
}

/*
 * glues
 */

/**
 * [Box.createGlue] and adds it to the [Container] you call it on
 */
fun Container.KGlue(addConstraints: Any? = null, addIndex: Int = -1, setup: Component.() -> Unit = {}): Component {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val glue = Box.createGlue()
	setup.invoke(glue)
	add(glue, addConstraints, addIndex)
	return glue
}

/**
 * [Box.createGlue]
 */
fun KLazyGlue(setup: Component.() -> Unit = {}): Component {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val glue = Box.createGlue()
	setup.invoke(glue)
	return glue
}

/**
 * [Box.createHorizontalGlue] and adds it to the [Container] you call it on
 */
fun Container.KHGlue(addConstraints: Any? = null, addIndex: Int = -1, setup: Component.() -> Unit = {}): Component {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val glue = Box.createHorizontalGlue()
	setup.invoke(glue)
	add(glue, addConstraints, addIndex)
	return glue
}

/**
 * [Box.createHorizontalGlue]
 */
fun KLazyHGlue(setup: Component.() -> Unit = {}): Component {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val glue = Box.createHorizontalGlue()
	setup.invoke(glue)
	return glue
}

/**
 * [Box.createVerticalGlue] and adds it to the [Container] you call it on
 */
fun Container.KVGlue(addConstraints: Any? = null, addIndex: Int = -1, setup: Component.() -> Unit = {}): Component {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val glue = Box.createVerticalGlue()
	setup.invoke(glue)
	add(glue, addConstraints, addIndex)
	return glue
}

/**
 * [Box.createHorizontalGlue]
 */
fun KLazyVGlue(setup: Component.() -> Unit = {}): Component {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val glue = Box.createVerticalGlue()
	setup.invoke(glue)
	return glue
}