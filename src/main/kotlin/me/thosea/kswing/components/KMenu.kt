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
import javax.swing.Action
import javax.swing.ButtonGroup
import javax.swing.Icon
import javax.swing.JCheckBoxMenuItem
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import javax.swing.JPopupMenu
import javax.swing.JRadioButtonMenuItem
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/*
 * menus
 */

/**
 * [JMenuBar]
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html)
 */
inline fun KLazyMenuBar(setup: JMenuBar.() -> Unit = {}): JMenuBar {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val bar = JMenuBar().apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(bar)
	return bar
}

/**
 * [JMenu] and adds it to the [Container] you call it on (usually a [JMenuBar] or [JPopupMenu])
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html)
 * @see KLazyMenuBar
 * @see KLazyPopupMenu
 */
inline fun Container.KMenu(
	text: String = "",
	action: Action? = null,
	buttonGroup: ButtonGroup? = null,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JMenu.() -> Unit = {},
): JMenu {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val menu = JMenu(text).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		action?.let { setAction(it) }
	}
	buttonGroup?.add(menu)
	setup.invoke(menu)
	add(menu, addConstraints, addIndex)
	return menu
}

/**
 * [JPopupMenu]
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html)
 * @see [JPopupMenu.show]
 */
inline fun KLazyPopupMenu(
	label: String? = null,
	setup: JPopupMenu.() -> Unit = {},
): JPopupMenu {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val menu = JPopupMenu(label).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	setup.invoke(menu)
	return menu
}

/*
 * items (no constraints - we want to call JMenu's add, not Container's add)
 */

/**
 * [JMenuItem] and adds it to the [Container] you call it on (usually [JMenuBar] or [JPopupMenu])
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html)
 */
inline fun Container.KMenuItem(
	text: String? = null, icon: Icon? = null,
	buttonGroup: ButtonGroup? = null,
	addIndex: Int = -1, setup: JMenuItem.() -> Unit = {},
): JMenuItem {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val menu = JMenuItem(text).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	buttonGroup?.add(menu)
	setup.invoke(menu)
	add(menu, addIndex)
	return menu
}

/**
 * [JMenuItem]
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html)
 */
inline fun KLazyMenuItem(
	text: String? = null, icon: Icon? = null,
	buttonGroup: ButtonGroup? = null,
	setup: JMenuItem.() -> Unit = {},
): JMenuItem {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val menu = JMenuItem(text).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	buttonGroup?.add(menu)
	setup.invoke(menu)
	return menu
}

/**
 * [JCheckBoxMenuItem] and adds it to the [Container] you call it on (usually [JMenuBar] or [JPopupMenu])
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html)
 */
inline fun Container.KCheckBoxMenuItem(
	text: String? = null, icon: Icon? = null,
	selected: Boolean = false,
	buttonGroup: ButtonGroup? = null,
	addIndex: Int = -1, setup: JCheckBoxMenuItem.() -> Unit = {},
): JCheckBoxMenuItem {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val menu = JCheckBoxMenuItem(text, icon, selected).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	buttonGroup?.add(menu)
	setup.invoke(menu)
	add(menu, addIndex)
	return menu
}

/**
 * [JCheckBoxMenuItem]
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html)
 */
inline fun KLazyCheckBoxMenuItem(
	text: String? = null, icon: Icon? = null,
	selected: Boolean = false,
	buttonGroup: ButtonGroup? = null,
	setup: JCheckBoxMenuItem.() -> Unit = {},
): JCheckBoxMenuItem {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val menu = JCheckBoxMenuItem(text, icon, selected).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	buttonGroup?.add(menu)
	setup.invoke(menu)
	return menu
}

/**
 * [JRadioButtonMenuItem] and adds it to the [Container] you call it on (usually [JMenuBar] or [JPopupMenu])
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html)
 */
inline fun Container.KRadioButtonMenuItem(
	text: String? = null, icon: Icon? = null,
	selected: Boolean = false,
	buttonGroup: ButtonGroup? = null,
	addIndex: Int = -1, setup: JRadioButtonMenuItem.() -> Unit = {},
): JRadioButtonMenuItem {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val menu = JRadioButtonMenuItem(text, icon, selected).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	buttonGroup?.add(menu)
	setup.invoke(menu)
	add(menu, addIndex)
	return menu
}

/**
 * [JRadioButtonMenuItem]
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html)
 */
inline fun KLazyRadioButtonMenuItem(
	text: String? = null, icon: Icon? = null,
	selected: Boolean = false,
	buttonGroup: ButtonGroup? = null,
	setup: JRadioButtonMenuItem.() -> Unit = {},
): JRadioButtonMenuItem {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val menu = JRadioButtonMenuItem(text, icon, selected).apply {
		alignmentX = Component.CENTER_ALIGNMENT
	}
	buttonGroup?.add(menu)
	setup.invoke(menu)
	return menu
}