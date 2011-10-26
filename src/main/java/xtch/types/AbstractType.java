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
package xtch.types;

import java.nio.ByteBuffer;
import java.util.Arrays;

public abstract class AbstractType<T> implements Type<T> {
  protected byte[] rightPadded(byte[] bytes, int length) {
    ByteBuffer buffer = ByteBuffer.allocate(length);
    Arrays.fill(buffer.array(), (byte) ' ');
    buffer.put(bytes);
    return buffer.array();
  }

  protected void write32bitsAsLittleEndian(ByteBuffer buffer, long value) {
    buffer.put((byte) (value >> 0  & 0xff));
    buffer.put((byte) (value >> 8  & 0xff));
    buffer.put((byte) (value >> 16 & 0xff));
    buffer.put((byte) (value >> 24 & 0xff));
  }

  protected long read32bitsAsLittleEndian(ByteBuffer buffer) {
    long result = 0;
    result |= (buffer.get() & 0xff) << 0;
    result |= (buffer.get() & 0xff) << 8;
    result |= (buffer.get() & 0xff) << 16;
    result |= (buffer.get() & 0xff) << 24;
    return result;
  }
}
