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

package me.thosea.kswing.listeners

import java.awt.Window
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.event.WindowListener

/**
 * Adds a [WindowListener]. You need to implement the methods yourself.
 * @see WindowAdapter
 */
fun Window.kWindowListener(handler: WindowListener) {
	addWindowListener(handler)
}

/**
 * Adds a [WindowListener] that only implements [WindowListener.windowOpened]
 */
fun Window.kWindowOpenedListener(handler: (WindowEvent) -> Unit) {
	addWindowListener(object: WindowAdapter() {
		override fun windowOpened(event: WindowEvent) {
			handler.invoke(event)
		}
	})
}

/**
 * Adds a [WindowListener] that only implements [WindowListener.windowClosing]
 */
fun Window.kWindowClosingListener(handler: (WindowEvent) -> Unit) {
	addWindowListener(object: WindowAdapter() {
		override fun windowClosing(event: WindowEvent) {
			handler.invoke(event)
		}
	})
}

/**
 * Adds a [WindowListener] that only implements [WindowListener.windowClosed]
 */
fun Window.kWindowClosedListener(handler: (WindowEvent) -> Unit) {
	addWindowListener(object: WindowAdapter() {
		override fun windowClosed(event: WindowEvent) {
			handler.invoke(event)
		}
	})
}

/**
 * Adds a [WindowListener] that only implements [WindowListener.windowIconified]
 */
// who named this "iconifiation" it's literally just minimize
fun Window.kWindowIconifiedListener(handler: (WindowEvent) -> Unit) {
	addWindowListener(object: WindowAdapter() {
		override fun windowIconified(event: WindowEvent) {
			handler.invoke(event)
		}
	})
}

/**
 * Adds a [WindowListener] that only implements [WindowListener.windowDeiconified]
 */
fun Window.kWindowDeiconifiedListener(handler: (WindowEvent) -> Unit) {
	addWindowListener(object: WindowAdapter() {
		override fun windowDeiconified(event: WindowEvent) {
			handler.invoke(event)
		}
	})
}

/**
 * Adds a [WindowListener] that only implements [WindowListener.windowActivated]
 */
fun Window.kWindowActivatedListener(handler: (WindowEvent) -> Unit) {
	addWindowListener(object: WindowAdapter() {
		override fun windowActivated(event: WindowEvent) {
			handler.invoke(event)
		}
	})
}

/**
 * Adds a [WindowListener] that only implements [WindowListener.windowDeactivated]
 */
fun Window.kWindowDeactivatedListener(handler: (WindowEvent) -> Unit) {
	addWindowListener(object: WindowAdapter() {
		override fun windowDeactivated(event: WindowEvent) {
			handler.invoke(event)
		}
	})
}