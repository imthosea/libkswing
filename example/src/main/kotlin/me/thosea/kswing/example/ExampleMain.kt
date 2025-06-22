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

import com.formdev.flatlaf.FlatLaf
import com.formdev.flatlaf.themes.FlatMacDarkLaf
import me.thosea.kswing.utils.KProperties
import javax.swing.JOptionPane
import javax.swing.SwingUtilities
import kotlin.system.exitProcess

fun main() {
	KProperties.macAppearance = "system"
	KProperties.macMenuBarName = "KSwing Example"

	FlatLaf.registerCustomDefaultsSource("me.thosea.kswing.example") // see resources folder
	FlatMacDarkLaf.setup()

	KProperties.useOpengl = true

	val options = arrayOf("Exit", "Character Creator", "Reminders", "Egg")
	val choice = JOptionPane.showOptionDialog(
		null,
		"What example do you want to run?",
		"KSwing Demo",
		JOptionPane.DEFAULT_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		null,
		options,
		options[0]
	)

	when(choice) {
		-1, 0 -> exitProcess(0)
		1 -> SwingUtilities.invokeLater { CharacterCreator.run() }
		2 -> SwingUtilities.invokeLater { Reminders.run() }
		3 -> SwingUtilities.invokeLater { Egg.run() }

		else -> throw AssertionError()
	}
}