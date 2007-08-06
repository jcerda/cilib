/*
 * ReinitialisationBoundary.java
 *
 * Created on June 22, 2007
 *
 * Copyright (C) 2003 - 2007
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science 
 * University of Pretoria
 * South Africa
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package net.sourceforge.cilib.pso.iterationstrategies;

import net.sourceforge.cilib.entity.Entity;

public class ReinitialisationBoundary implements BoundaryConstraint {
	private static final long serialVersionUID = -512973040124015665L;

	public void enforce(Entity entity) {
		if (!entity.getContents().isInsideBounds()) {
//			log.warn("Enity outside bounds; reinitialising");
			entity.reinitialise();
		}
	}
}