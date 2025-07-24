package com.delicacy.client;




public class DownloadFileByUrl {
	
	public static native void download(String url)/*-{
		var form = $doc.createElement('form');
		form.action = url;
		form.target = '_blank';
		form.method = 'GET';
		document.body.appendChild(form);
		form.submit();
		form.remove();
	}-*/;




}
