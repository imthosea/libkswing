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

import java.awt.Container
import java.awt.event.ContainerAdapter
import java.awt.event.ContainerEvent
import java.awt.event.ContainerListener

/**
 * Adds a [ContainerListener]. You need to implement the methods yourself.
 * @see ContainerAdapter
 */
fun Container.kContainerListener(handler: ContainerListener) {
	addContainerListener(handler)
}

/**
 * Adds a [ContainerListener] that implements
 * [ContainerListener.componentAdded] and [ContainerListener.componentRemoved]
 */
fun Container.kContainerListener(
	componentAdded: (ContainerEvent) -> Unit,
	componentRemoved: (ContainerEvent) -> Unit,
) {
	addContainerListener(object: ContainerListener {
		override fun componentAdded(event: ContainerEvent) {
			componentAdded.invoke(event)
		}

		override fun componentRemoved(event: ContainerEvent) {
			componentRemoved.invoke(event)
		}
	})
}

/**
 * Adds a [ContainerListener] that only implements [ContainerListener.componentAdded]
 */
fun Container.kContainerComponentAddedListener(handler: (ContainerEvent) -> Unit) {
	addContainerListener(object: ContainerAdapter() {
		override fun componentAdded(event: ContainerEvent) {
			handler.invoke(event)
		}
	})
}

/**
 * Adds a [ContainerListener] that only implements [ContainerListener.componentRemoved]
 */
fun Container.kContainerComponentRemovedListener(handler: (ContainerEvent) -> Unit) {
	addContainerListener(object: ContainerAdapter() {
		override fun componentRemoved(event: ContainerEvent) {
			handler.invoke(event)
		}
	})
}