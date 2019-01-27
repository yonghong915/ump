package com.ump.commons.encryption;

public final class DigestAlgorithms {
	private DigestAlgorithms() {
	}

	public static final String THREE_DES = "DESede";

	public static final String DES = "DES";

	public static final String AES = "AES";

	public static final String RSA = "RSA";

	/**
	 * The MD5 message digest algorithm defined in RFC 1321.
	 */
	public static final String MD5 = "MD5";

	/**
	 * The SHA-1 hash algorithm defined in the FIPS PUB 180-2.
	 */
	public static final String SHA_1 = "SHA-1";

	/**
	 * The SHA-224 hash algorithm defined in the FIPS PUB 180-3.
	 * <p>
	 * Present in Oracle Java 8.
	 * </p>
	 *
	 * @since 1.11
	 */
	public static final String SHA_224 = "SHA-224";

	/**
	 * The SHA-256 hash algorithm defined in the FIPS PUB 180-2.
	 */
	public static final String SHA_256 = "SHA-256";

	/**
	 * The SHA-384 hash algorithm defined in the FIPS PUB 180-2.
	 */
	public static final String SHA_384 = "SHA-384";

	/**
	 * The SHA-512 hash algorithm defined in the FIPS PUB 180-2.
	 */
	public static final String SHA_512 = "SHA-512";

	/**
	 * The SHA3-224 hash algorithm defined in the FIPS PUB 202.
	 * <p>
	 * Likely to be included in Oracle Java 9 GA.
	 * </p>
	 *
	 * @since 1.11
	 */
	public static final String SHA3_224 = "SHA3-224";

	/**
	 * The SHA3-256 hash algorithm defined in the FIPS PUB 202.
	 * <p>
	 * Likely to be included in Oracle Java 9 GA.
	 * </p>
	 *
	 * @since 1.11
	 */
	public static final String SHA3_256 = "SHA3-256";

	/**
	 * The SHA3-384 hash algorithm defined in the FIPS PUB 202.
	 * <p>
	 * Likely to be included in Oracle Java 9 GA.
	 * </p>
	 *
	 * @since 1.11
	 */
	public static final String SHA3_384 = "SHA3-384";

	/**
	 * The SHA3-512 hash algorithm defined in the FIPS PUB 202.
	 * <p>
	 * Likely to be included in Oracle Java 9 GA.
	 * </p>
	 *
	 * @since 1.11
	 */
	public static final String SHA3_512 = "SHA3-512";
}
