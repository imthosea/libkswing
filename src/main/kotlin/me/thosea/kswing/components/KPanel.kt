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

import me.thosea.kswing.utils.KConstants.kEmptyBorder
import java.awt.BorderLayout
import java.awt.CardLayout
import java.awt.Component
import java.awt.Container
import java.awt.FlowLayout
import java.awt.GridBagLayout
import java.awt.GridLayout
import java.awt.LayoutManager
import javax.swing.BoxLayout
import javax.swing.GroupLayout
import javax.swing.JPanel
import javax.swing.SpringLayout
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * [JPanel] and adds to the [Container] you call it on
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 */
inline fun Container.KPanel(
	layout: LayoutManager? = null,
	isDoubleBuffered: Boolean = true,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(layout, isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		border = kEmptyBorder
	}
	setup.invoke(panel)
	add(panel, addConstraints, addIndex)
	return panel
}

/**
 * [JPanel]
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 */
inline fun KLazyPanel(
	layout: LayoutManager? = null,
	isDoubleBuffered: Boolean = true,
	setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(layout, isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		border = kEmptyBorder
	}
	setup.invoke(panel)
	return panel
}

/**
 * [JPanel] with [BorderLayout] and adds it to the [Container] you call it on
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [BorderLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html)
 */
inline fun Container.KBorderPanel(
	hgap: Int = 0, vgap: Int = 0,
	isDoubleBuffered: Boolean = true,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(BorderLayout(hgap, vgap), isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		border = kEmptyBorder
	}
	setup.invoke(panel)
	add(panel, addConstraints, addIndex)
	return panel
}

/**
 * [JPanel] with [BorderLayout]
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [BorderLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html)
 */
inline fun KLazyBorderPanel(
	hgap: Int = 0, vgap: Int = 0,
	isDoubleBuffered: Boolean = true,
	setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(BorderLayout(hgap, vgap), isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		border = kEmptyBorder
	}
	setup.invoke(panel)
	return panel
}

/**
 * [JPanel] with [BoxLayout] and adds it to the [Container] you call it on
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [BoxLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html)
 */
inline fun Container.KBoxPanel(
	axis: Int,
	isDoubleBuffered: Boolean = true,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		layout = BoxLayout(this, axis)
		border = kEmptyBorder
	}
	setup.invoke(panel)
	add(panel, addConstraints, addIndex)
	return panel
}

/**
 * [JPanel] with [BoxLayout]
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [BoxLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html)
 */
inline fun KLazyBoxPanel(
	axis: Int,
	isDoubleBuffered: Boolean = true,
	setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		layout = BoxLayout(this, axis)
		border = kEmptyBorder
	}
	setup.invoke(panel)
	return panel
}

/**
 * [JPanel] with X-axis [BoxLayout] and adds it to the [Container] you call it on
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [BoxLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html)
 */
inline fun Container.KHBoxPanel(
	isDoubleBuffered: Boolean = true,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		layout = BoxLayout(this, BoxLayout.X_AXIS)
		border = kEmptyBorder
	}
	setup.invoke(panel)
	add(panel, addConstraints, addIndex)
	return panel
}

/**
 * [JPanel] with X-axis [BoxLayout]
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [BoxLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html)
 */
inline fun KLazyHBoxPanel(
	isDoubleBuffered: Boolean = true,
	setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		layout = BoxLayout(this, BoxLayout.X_AXIS)
		border = kEmptyBorder
	}
	setup.invoke(panel)
	return panel
}

/**
 * [JPanel] with Y-axis [BoxLayout] and adds it to the [Container] you call it on
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [BoxLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html)
 */
inline fun Container.KVBoxPanel(
	isDoubleBuffered: Boolean = true,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		layout = BoxLayout(this, BoxLayout.Y_AXIS)
		border = kEmptyBorder
	}
	setup.invoke(panel)
	add(panel, addConstraints, addIndex)
	return panel
}

/**
 * [JPanel] with Y-axis [BoxLayout]
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [BoxLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html)
 */
inline fun KLazyVBoxPanel(
	isDoubleBuffered: Boolean = true,
	setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		layout = BoxLayout(this, BoxLayout.Y_AXIS)
		border = kEmptyBorder
	}
	setup.invoke(panel)
	return panel
}

/**
 * [JPanel] with [CardLayout] and adds it to the [Container] you call it on
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [CardLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html)
 */
inline fun Container.KCardPanel(
	hgap: Int = 0, vgap: Int = 0,
	isDoubleBuffered: Boolean = true,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(CardLayout(hgap, vgap), isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		border = kEmptyBorder
	}
	setup.invoke(panel)
	add(panel, addConstraints, addIndex)
	return panel
}

/**
 * [JPanel] with [CardLayout]
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [CardLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html)
 */
inline fun KLazyCardPanel(
	hgap: Int = 0, vgap: Int = 0,
	isDoubleBuffered: Boolean = true,
	setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(CardLayout(hgap, vgap), isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		border = kEmptyBorder
	}
	setup.invoke(panel)
	return panel
}

/**
 * [JPanel] with [FlowLayout] and adds it to the [Container] you call it on
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [FlowLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/flow.html)
 */
inline fun Container.KFlowPanel(
	align: Int = FlowLayout.CENTER, hgap: Int = 5, vgap: Int = 5,
	isDoubleBuffered: Boolean = true,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(FlowLayout(align, hgap, vgap), isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		border = kEmptyBorder
	}
	setup.invoke(panel)
	add(panel, addConstraints, addIndex)
	return panel
}

/**
 * [JPanel] with [FlowLayout]
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [FlowLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/flow.html)
 */
inline fun KLazyFlowPanel(
	align: Int = FlowLayout.CENTER, hgap: Int = 5, vgap: Int = 5,
	isDoubleBuffered: Boolean = true,
	setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(FlowLayout(align, hgap, vgap), isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		border = kEmptyBorder
	}
	setup.invoke(panel)
	return panel
}

/**
 * [JPanel] with [GridBagLayout] and adds it to the [Container] you call it on
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [GridBagLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html)
 */
inline fun Container.KGridBagPanel(
	isDoubleBuffered: Boolean = true,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(GridBagLayout(), isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		border = kEmptyBorder
	}
	setup.invoke(panel)
	add(panel, addConstraints, addIndex)
	return panel
}

/**
 * [JPanel] with [GridBagLayout]
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [GridBagLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html)
 */
inline fun KLazyGridBagPanel(
	isDoubleBuffered: Boolean = true,
	setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(GridBagLayout(), isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		border = kEmptyBorder
	}
	setup.invoke(panel)
	return panel
}

/**
 * [JPanel] with [GridLayout] and adds it to the [Container] you call it on
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [GridLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html)
 */
inline fun Container.KGridPanel(
	rows: Int = 1, columns: Int = 0, hgap: Int = 0, vgap: Int = 0,
	isDoubleBuffered: Boolean = true,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(GridLayout(rows, columns, hgap, vgap), isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		border = kEmptyBorder
	}
	setup.invoke(panel)
	add(panel, addConstraints, addIndex)
	return panel
}

/**
 * [JPanel] with [GridLayout]
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [GridLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html)
 */
inline fun KLazyGridPanel(
	rows: Int = 1, columns: Int = 0, hgap: Int = 0, vgap: Int = 0,
	isDoubleBuffered: Boolean = true,
	setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(GridLayout(rows, columns, hgap, vgap), isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		border = kEmptyBorder
	}
	setup.invoke(panel)
	return panel
}

/**
 * [JPanel] with [GroupLayout] and adds it to the [Container] you call it on
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [GroupLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/group.html)
 */
inline fun Container.KGroupPanel(
	isDoubleBuffered: Boolean = true,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		layout = GroupLayout(this)
		border = kEmptyBorder
	}
	setup.invoke(panel)
	add(panel, addConstraints, addIndex)
	return panel
}

/**
 * [JPanel] with [GroupLayout]
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [GroupLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/group.html)
 */
inline fun KLazyGroupPanel(
	isDoubleBuffered: Boolean = true,
	setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		layout = GroupLayout(this)
		border = kEmptyBorder
	}
	setup.invoke(panel)
	return panel
}

/**
 * [JPanel] with [SpringLayout] and adds it to the [Container] you call it on
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [SpringLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/spring.html)
 */
inline fun Container.KSpingPanel(
	isDoubleBuffered: Boolean = true,
	addConstraints: Any? = null, addIndex: Int = -1, setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(SpringLayout(), isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		border = kEmptyBorder
	}
	setup.invoke(panel)
	add(panel, addConstraints, addIndex)
	return panel
}

/**
 * [JPanel] with [SpringLayout]
 *
 * [JPanel Docs](https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)
 * [SpringLayout Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/spring.html)
 */
inline fun KLazySpringPanel(
	isDoubleBuffered: Boolean = true,
	setup: JPanel.() -> Unit = {},
): JPanel {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val panel = JPanel(SpringLayout(), isDoubleBuffered).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		border = kEmptyBorder
	}
	setup.invoke(panel)
	return panel
}