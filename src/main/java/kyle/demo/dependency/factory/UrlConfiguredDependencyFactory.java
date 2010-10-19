package kyle.demo.dependency.factory;

import kyle.demo.dependency.Dependency;

public class UrlConfiguredDependencyFactory extends UrlConfiguredBeanLookupFactory<Dependency> implements
		DependencyFactory {

	public UrlConfiguredDependencyFactory(Dependency defaultBean,
			String configUrl, String lookupPropertyName) {
		super(defaultBean, configUrl, lookupPropertyName);
	}

	@Override
	public Dependency getDependency() {
		return getBean();
	}


}
