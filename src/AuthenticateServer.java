import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class AuthenticateServer {
	public static PublicKey authServer(X509Certificate cert) throws Exception{
		try {

			//Server's public key loaded from the file
			String publicKeyStr = new String(Files.readAllBytes(Paths.get("serverpubkey")));
			
			//getting bytes from the server's public key string
			byte[] data = Base64.getDecoder().decode(publicKeyStr.getBytes());
			
			//Generating key spec of the server's public key
			X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			PublicKey serverPK = kf.generatePublic(spec);
			
			//Verifying the certificate by comparing to Server's public key
			cert.verify(serverPK);
			return serverPK;
		}
		catch(Exception e) {
			throw e;
		}
	}
}
