package kyle.demo.service;

import kyle.demo.dependency.Dependency;

public class ServiceImpl implements Service {

	private Dependency dependency;
	
	public ServiceImpl(Dependency dependency) {
		super();
		this.dependency = dependency;
	}

	@Override
	public String callService() {
		
		return dependency.someRemoteCall();
		
	}

}
