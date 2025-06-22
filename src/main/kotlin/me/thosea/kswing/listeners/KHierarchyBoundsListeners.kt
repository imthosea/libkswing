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

import java.awt.Component
import java.awt.event.HierarchyBoundsAdapter
import java.awt.event.HierarchyBoundsListener
import java.awt.event.HierarchyEvent

/**
 * Adds a [HierarchyBoundsListener]. You need to implement the methods yourself.
 * @see HierarchyBoundsAdapter
 */
fun Component.kHierarchyBoundsListener(handler: HierarchyBoundsListener) {
	addHierarchyBoundsListener(handler)
}

/**
 * Adds a [HierarchyBoundsListener] that implements
 * [HierarchyBoundsListener.ancestorMoved] and [HierarchyBoundsListener.ancestorResized]
 */
fun Component.kHierarchyBoundsListener(
	ancestorMoved: (HierarchyEvent) -> Unit,
	ancestorResized: (HierarchyEvent) -> Unit,
) {
	addHierarchyBoundsListener(object: HierarchyBoundsListener {
		override fun ancestorMoved(event: HierarchyEvent) {
			ancestorMoved.invoke(event)
		}

		override fun ancestorResized(event: HierarchyEvent) {
			ancestorResized.invoke(event)
		}
	})
}

/**
 * Adds a [HierarchyBoundsListener] that only implements [HierarchyBoundsListener.ancestorMoved]
 */
// what a mouthful
fun Component.kHierarchyBoundsAncestorMovedListener(handler: (HierarchyEvent) -> Unit) {
	addHierarchyBoundsListener(object: HierarchyBoundsAdapter() {
		override fun ancestorMoved(event: HierarchyEvent) {
			handler.invoke(event)
		}
	})
}

/**
 * Adds a [HierarchyBoundsListener] that only implements [HierarchyBoundsListener.ancestorResized]
 */
fun Component.kHierarchyBoundsAncestorResizedListener(handler: (HierarchyEvent) -> Unit) {
	addHierarchyBoundsListener(object: HierarchyBoundsAdapter() {
		override fun ancestorResized(event: HierarchyEvent) {
			handler.invoke(event)
		}
	})
}