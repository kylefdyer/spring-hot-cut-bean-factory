package kyle.demo.dependency;

public class DependencyNew implements Dependency {

	@Override
	public String someRemoteCall() {
		return "the new one";
	}

}
