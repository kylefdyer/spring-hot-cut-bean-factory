package kyle.demo.dependency;

public class DependencyOld implements Dependency {

	@Override
	public String someRemoteCall() {
		return "the old one";
	}

}
