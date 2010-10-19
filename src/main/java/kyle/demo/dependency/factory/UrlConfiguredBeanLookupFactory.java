package kyle.demo.dependency.factory;

import kyle.demo.dependency.Dependency;

public class UrlConfiguredBeanLookupFactory extends UrlConfiguredBeanLookupDependencyFactory<Dependency> implements
		DependencyFactory {

	public UrlConfiguredBeanLookupFactory(Dependency defaultBean,
			String configUrl, String lookupPropertyName) {
		super(defaultBean, configUrl, lookupPropertyName);
	}

	@Override
	public Dependency getDependency() {
		return getBean();
	}


}
