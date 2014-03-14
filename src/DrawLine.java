
public class DrawLine {
	public void drawHorizontalLin(byte[] screen, int width, int x1, int x2, int y) {
		int startOffset = x1 % 8;
		int firstFullByte = x1 / 8;
		if (startOffset != 0) {
			firstFullByte++;
		}
		int endOffset = x2 % 8;
		int lastFullByte = x2 / 8;
		if (endOffset != 7) {
			lastFullByte--;
		}
		// Set full bytes
		for (int b = firstFullByte; b <= lastFullByte; b++) {
			screen[(width / 8) * y + b] = (byte) 0xFF;
		}
		// Create masks for start and end of line
		byte startMask = (byte) (0xFF >> startOffset);
		byte endMask = (byte) ~(0xFF >> (endOffset + 1));
		// Set start and end of line
		if ((x1 / 8) == (x2 / 8)) { // x1 and x2 are in the same byte
			byte mask = (byte) (startMask & endMask);
			screen[(width / 8) * y + (x1 / 8)] |= mask;
		} else {
			if (startOffset != 0) {
				int byteNumber = (width / 8) * y + firstFullByte - 1;
				screen[byteNumber] |= startMask;
			}
			if (endOffset != 7) {
				int byteNumber = (width / 8) * y + lastFullByte + 1;
				screen[byteNumber] |= endMask;
			}
		}
	}
}
