package comm;


public class Lookup {

	public static AbsoluteObjectReference lookup(String endpoint, String clazzName, String objectId) {
		AbsoluteObjectReference aor = new AbsoluteObjectReference();
		aor.setEndpoint(endpoint);
		aor.setObjectId(objectId);
		aor.setClazzName(clazzName);
		return aor;
	}
}
