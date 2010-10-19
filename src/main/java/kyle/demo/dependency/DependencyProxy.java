package kyle.demo.dependency;

import kyle.demo.dependency.factory.DependencyFactory;

public class DependencyProxy implements Dependency  {
	
	private DependencyFactory dependencyFactory;

	public DependencyProxy(DependencyFactory dependencyFactory){
		this.dependencyFactory = dependencyFactory;
	}
	
	@Override
	public String someRemoteCall() {
		return dependencyFactory.getDependency().someRemoteCall();
	}
	
}
