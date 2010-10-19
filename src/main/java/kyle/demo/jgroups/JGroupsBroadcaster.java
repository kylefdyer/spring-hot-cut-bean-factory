package kyle.demo.jgroups;

import kyle.demo.dependency.factory.UrlConfiguredBeanLookupFactory;

import org.jgroups.ChannelException;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.springframework.beans.factory.DisposableBean;

/**
 * Broadcast changes to every node in the cluster
 */
public class JGroupsBroadcaster extends ReceiverAdapter implements DisposableBean  {
	
	JChannel channel;
	private UrlConfiguredBeanLookupFactory<?> lookupFactory;
	

	public JGroupsBroadcaster(UrlConfiguredBeanLookupFactory<?> lookupFactory) throws ChannelException {
		this.lookupFactory = lookupFactory;
		lookupFactory.setBroadcaster(this);
		channel = new JChannel();
		channel.setReceiver(this);
		channel.connect("DependencyLookupCacheListener");
	}
	
	public void refreshAllNodes() {
		Message msg = new Message(null, null, "refresh");
		try {
			System.out.println("** Send invalidate cache message.");
			channel.send(msg);
		} catch (ChannelException e) {
			e.printStackTrace();
		} 
	}
	
	@Override
	public void viewAccepted(View new_view) {
		System.out.println("** view: " + new_view);
	}

	
	@Override
	public void receive(Message msg) {
		System.out.println("** Received invalidate cache message.");
		lookupFactory.invalidateCache();
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("** Closing channel.");
		channel.close();
	}



	
}
