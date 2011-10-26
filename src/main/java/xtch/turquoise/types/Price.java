/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xtch.turquoise.types;

import java.nio.ByteBuffer;
import xtch.types.AbstractType;

public class Price extends AbstractType<Double> {
  public static final Price TYPE = new Price();

  private static final long FRAC_TO_INT_FACTOR = 100000000L;

  @Override public void encode(ByteBuffer buffer, Double value, int length) {
    long integer = value.longValue();
    write32bitsAsLittleEndian(buffer, integer);

    long fractional = (long) ((double) (value - integer) * FRAC_TO_INT_FACTOR);
    write32bitsAsLittleEndian(buffer, fractional);
  }

  @Override public Double decode(ByteBuffer buffer, int length) {
    return read32bitsAsLittleEndian(buffer) + (double) read32bitsAsLittleEndian(buffer) / (double) FRAC_TO_INT_FACTOR;
  }

}
