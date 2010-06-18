/**
 * Computational Intelligence Library (CIlib)
 * Copyright (C) 2003 - 2010
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science
 * University of Pretoria
 * South Africa
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, see <http://www.gnu.org/licenses/>.
 */
package net.sourceforge.cilib.pso.velocityupdatestrategies;

import net.sourceforge.cilib.type.types.container.Vector;
import net.sourceforge.cilib.entity.Particle;
import net.sourceforge.cilib.math.random.GaussianDistribution;
import net.sourceforge.cilib.math.random.ProbabilityDistributionFuction;

/**
 * Decorates a {@link PositionUpdateVisitor} or a {@link VelocityUpdateVisitor}
 * with random noise from any probability distribution function.
 *
 * @author Bennie Leonard
 */
public class NoisyVelocityUpdateDecorator implements VelocityUpdateStrategy {

    private static final long serialVersionUID = -4398497101382747367L;
    private ProbabilityDistributionFuction distribution;
    private VelocityUpdateStrategy delegate;

    public NoisyVelocityUpdateDecorator() {
        this.distribution = new GaussianDistribution();
        this.delegate = new StandardVelocityUpdate();
    }

    public NoisyVelocityUpdateDecorator(NoisyVelocityUpdateDecorator rhs) {
        this.distribution = rhs.distribution;
        this.delegate = rhs.delegate.getClone();
    }

    @Override
    public Vector get(Particle particle) {
        Vector velocity = this.delegate.get(particle);
        Vector.Builder builder = new Vector.Builder();
        for (int i = 0; i < velocity.size(); i++) {
            builder.add(velocity.get(i).doubleValue() + this.distribution.getRandomNumber());
        }
        return builder.build();
    }

    @Override
    public NoisyVelocityUpdateDecorator getClone() {
        return new NoisyVelocityUpdateDecorator(this);
    }

    @Override
    public void updateControlParameters(Particle particle) {
        delegate.updateControlParameters(particle);
    }

    public VelocityUpdateStrategy getDelegate() {
        return delegate;
    }

    public void setDelegate(VelocityUpdateStrategy delegate) {
        this.delegate = delegate;
    }

    public ProbabilityDistributionFuction getDistribution() {
        return distribution;
    }

    public void setDistribution(ProbabilityDistributionFuction distribution) {
        this.distribution = distribution;
    }
}