package `fun`.gladkikh.app.fastcargo.remote.util

import java.net.InetAddress
import java.net.Socket
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory

class Tls12SocketFactory(private val delegate: SSLSocketFactory) : SSLSocketFactory() {

    companion object {
        private val TLS_V12_ONLY = arrayOf("TLSv1.2")
    }

    private fun patch(s: Socket): Socket {
        if (s is SSLSocket) s.enabledProtocols = TLS_V12_ONLY
        return s
    }

    override fun getDefaultCipherSuites(): Array<out String> = delegate.defaultCipherSuites

    override fun getSupportedCipherSuites(): Array<out String> = delegate.supportedCipherSuites

    override fun createSocket(socket: Socket, host: String, port: Int, autoClose: Boolean)
            = patch(delegate.createSocket(socket, host, port, autoClose))

    override fun createSocket(host: InetAddress, port: Int)
            = patch(delegate.createSocket(host, port))

    override fun createSocket(address: InetAddress, port: Int, localAddress: InetAddress, localPort: Int)
            = patch(delegate.createSocket(address, port, localAddress, localPort))

    override fun createSocket(host: String, port: Int)
            = patch(delegate.createSocket(host, port))

    override fun createSocket(host: String, port: Int, localAddress: InetAddress, localPort: Int)
            = patch(delegate.createSocket(host, port, localAddress, localPort))

}

