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

package me.thosea.kswing.example

import me.thosea.kswing.components.KButton
import me.thosea.kswing.components.KFlowPanel
import me.thosea.kswing.components.KHGlue
import me.thosea.kswing.components.KLabel
import me.thosea.kswing.listeners.kActionListener
import me.thosea.kswing.utils.kBagConstraints
import java.awt.BorderLayout
import java.awt.Frame
import java.awt.GridBagLayout
import java.awt.Insets
import java.util.*
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.UIManager
import kotlin.system.exitProcess

/**
 * Recreation of a JDialog with different layouts
 */
object Egg {
	fun run() {
		// the man behind the tree would be proud
		println("Running Eggs")

		val dialog = JDialog(null as Frame?, null, false)
		dialog.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
		dialog.isResizable = false
		dialog.setSize(395, 150)
		dialog.setLocationRelativeTo(null)

		val choice = JOptionPane.showOptionDialog(
			null,
			"What layout do you want your eggs?",
			"Egg Layout",
			JOptionPane.DEFAULT_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			arrayOf("Quit", "BorderLayout", "GridBagLayout"),
			null
		)

		when(choice) {
			-1, 0 -> exitProcess(0)
			1 -> dialog.apply { setupByBox() }
			2 -> dialog.apply { setupByGridBag() }
		}

		dialog.isVisible = true
	}

	private fun JDialog.setupByBox() {
		title = "KSwing Message (BorderLayout)"

		KFlowPanel(addConstraints = BorderLayout.CENTER) {
			KHGlue()
			KLabel(icon = UIManager.getIcon("OptionPane.informationIcon"))
			KHGlue()
			KLabel("Eggs are supposed to be green.")
			KHGlue()
		}
		// wrap in panel to center
		KFlowPanel(addConstraints = BorderLayout.PAGE_END) {
			KButton("OK").kActionListener { exitProcess(0) }
		}
	}

	private fun JDialog.setupByGridBag() {
		title = "KSwing Message (GridBagLayout)"
		contentPane.layout = GridBagLayout()

		KLabel(
			icon = UIManager.getIcon("OptionPane.informationIcon"),
			addConstraints = kBagConstraints(
				gridx = 0, gridy = 0,
				insets = Insets(10, 0, 0, 0)
			)
		)

		KLabel(
			if(Random().nextInt(50) != 1) {
				"Eggs aren't supposed to be green."
			} else {
				"Eggs are supposed to be green."
			},
			addConstraints = kBagConstraints(
				gridx = 1, gridy = 0,
				insets = Insets(6, 0, 0, 0)
			)
		)

		KButton("OK", addConstraints = kBagConstraints(
			gridx = 0, gridy = 1,
			gridwidth = 2,
			weighty = 1.0
		)) {
			kActionListener { exitProcess(0) }
		}
	}
}