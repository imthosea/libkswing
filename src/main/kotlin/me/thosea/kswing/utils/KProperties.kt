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

@file:Suppress("Unused")

package me.thosea.kswing.utils

import kotlin.reflect.KProperty

/**
 * Wrappers for some AWT/Swing-related system properties
 */
object KProperties {
	/**
	 * Delegates to property `sun.java2d.opengl` allowing
	 * hardware acceleration for Swing GUIs. Usually cause issues
	 * with `JDialog`s.
	 *
	 * Default: false
	 */
	var useOpengl: Boolean by BooleanProp("sun.java2d.opengl")

	/**
	 * Delegates to property `apple.laf.useScreenMenuBar` making
	 * menu bars set by [javax.swing.JFrame.setJMenuBar] appear in the
	 * macOS menu bar instead of on the window.
	 * Has no effect on any platform other than macOS.
	 *
	 * Default: false
	 */
	var macUseScreenMenuBar: Boolean by BooleanProp("apple.laf.useScreenMenuBar")

	/**
	 * Delegates to property `apple.laf.useScreenMenuBar` making
	 * menu bars set by [javax.swing.JFrame.setJMenuBar] appear in the
	 * macOS menu bar instead of on the window.
	 * Has no effect on any platform other than macOS.
	 *
	 * Default: `null` (Uses your main class name)
	 */
	var macMenuBarName: String? by StringProp("apple.awt.application.name")

	/**
	 * Delegates to property `apple.awt.application.appearance` deciding
	 * the appearance of the title bar. Does not affect window contents.
	 * This must be set on the main thread BEFORE initializing Swing.
	 * Has no effect on any platform other than macOS.
	 *
	 * Values:
	 * - `system` - uses system setting
	 * - `NSAppearanceNameAqua` - light mode (default)
	 * - `NSAppearanceNameDarkAqua` - dark mode
	 *
	 * Default: `null` (uses light mode)
	 */
	var macAppearance: String? by StringProp("apple.awt.application.appearance")

	/**
	 * Delegates to property `sun.java2d.uiScale` changing the global scale
	 * of all components.
	 *
	 * Default: `null` (1)
	 */
	var uiScale: String? by StringProp("sun.java2d.uiScale")

	private class BooleanProp(val key: String) {
		operator fun getValue(thus: Any?, property: KProperty<*>): Boolean {
			return System.getProperty(key).toBoolean()
		}

		operator fun setValue(thus: Any?, property: KProperty<*>, value: Boolean) {
			System.setProperty(key, value.toString())
		}
	}

	private class StringProp(val key: String) {
		operator fun getValue(thus: Any?, property: KProperty<*>): String? {
			return System.getProperty(key)
		}

		operator fun setValue(thus: Any?, property: KProperty<*>, value: String?) {
			if(value == null) {
				System.clearProperty(key)
			} else {
				System.setProperty(key, value)
			}
		}
	}
}