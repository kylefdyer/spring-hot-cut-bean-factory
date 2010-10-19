package kyle.demo.dependency.factory;

import java.net.URL;
import java.util.Properties;

import kyle.demo.jgroups.JGroupsBroadcaster;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class UrlConfiguredBeanLookupFactory<T> implements
		 BeanFactoryAware {

	private T cachedBean = null;
	private T defaultBean = null;
	private BeanFactory beanFactory;
	
	private String configUrl;
	private String lookupPropertyName;
	private JGroupsBroadcaster jGroupsBroadcaster;

	public UrlConfiguredBeanLookupFactory(
			T defaultBean, String configUrl, String lookupPropertyName) {
		
		if (defaultBean == null || configUrl == null || lookupPropertyName == null) {
			throw new RuntimeException("no constructor params can be null.");
		}
		this.defaultBean = defaultBean;
		this.configUrl = configUrl;
		this.lookupPropertyName = lookupPropertyName;
	}

	public T getBean() {
		if (cachedBean == null) {
			lookupAndCacheDependency();
		}
		return cachedBean;
	}


	@SuppressWarnings("unchecked")
	private void lookupAndCacheDependency() {
		try {
			URL url = new URL(configUrl); // "http://localhost:8080/config.txt"
			Properties props = new Properties();
			props.load(url.openStream());

			String beanName = props.getProperty(lookupPropertyName); // "kyle.demo.dependency.DependencyProxy"
			cachedBean = (T) beanFactory.getBean(beanName);
		} catch (Exception e) {
			System.err.println("Sucks.  Couldn't lookup the right one.  " +
					"Using the default. "+ defaultBean.getClass());
			e.printStackTrace();
			cachedBean = defaultBean;
		}

	}

	public void invalidateCacheAndBroadcast() {
		invalidateCache();
		this.jGroupsBroadcaster.refreshAllNodes();
	}
	
	public void invalidateCache() {
		cachedBean = null;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	public void setBroadcaster(JGroupsBroadcaster jGroupsBroadcaster) {
		this.jGroupsBroadcaster = jGroupsBroadcaster;
	}



}
