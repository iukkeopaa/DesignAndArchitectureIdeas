## ����NIO�ķ�����I/Oģ��


### NIO ������ I/O ģ�ͺ���˼��������ͨ������������ѡ���������¼�������ʽʹ�߳��ڴ����� I/O ����ʱ��������������ϵͳ����������������Դ�����ʡ�

���� NIO��New I/O���ķ����� I/O ģ�͵ĺ���˼����**ͨ��ʹ��ͨ����Channel������������Buffer����ѡ������Selector����ʵ�ָ�Ч�� I/O ������ʹ���ڽ��� I/O ����ʱ���̲߳��ᱻ�������Ӷ����ϵͳ�Ĳ�����������**�������Ǿ�����ͣ�



- **ͨ����Channel��**��ͨ���ǶԴ�ͳ I/O ����һ�ֳ��������������ⲿ�豸�����ļ����������ӵȣ��������ݽ�����ͨ�����봫ͳ������ͬ��ͨ�����Խ���˫������ݴ��䣬����֧�ַ�����ʽ�Ķ�д������
- **��������Buffer��**�����������ڴ洢���ݣ�����ͨ����Ӧ�ó���֮�����ݴ�����м�ý�顣���ݴ�ͨ����ȡ�������������ߴӻ�����д�뵽ͨ�����������ṩ��һ�ָ�Ч�����ݴ洢�Ͳ�����ʽ�������������ڲ�ͬ���֮��ĸ��ƺʹ��俪����
- **ѡ������Selector��**��ѡ������ NIO �ĺ������֮һ�������ڼ�ض��ͨ�����¼�����ɶ�����д�����Ӿ����ȣ���ͨ��ѡ������һ���߳̿���ͬʱ������ͨ����������ҪΪÿ��ͨ��������һ���������̡߳���ͨ�����и���Ȥ���¼�����ʱ��ѡ������֪ͨ��Ӧ���߳̽��д����Ӷ�ʵ���˻����¼������ķ����� I/O ������



�ڻ��� NIO �ķ����� I/O ģ���У��߳̿���ע��Զ��ͨ���Ĳ�ͬ�¼�����Ȥ��Ȼ��ͨ��ѡ�������ȴ��¼��ķ����������¼�����ʱ���߳�ֻ�ᴦ�����¼���ͨ���������ᱻ����������û���¼�������ͨ���ϡ����ַ�ʽʹ���߳̿��Ը���Ч������ CPU ��Դ��ͬʱ������ I/O �����������ϵͳ�Ĳ������ܺ���Ӧ������


## �¼�����ģ��

�¼�����ģ����һ�ֳ�����Ʒ�ʽ�������˼���ǳ����ִ���������¼��ķ���������������Ϊ����ϸ���ܣ�

### ��������

- **�¼�**��ϵͳ�з������ض����飬���û������ꡢ�������ݵ����ʱ����ʱ�ȡ�
- **�¼�Դ**�������¼��Ķ�����������ͼ�ν����еİ�ť�������׽��ֵȡ�
- **�¼�������**����������ض��¼��Ĵ���飬���¼�����ʱ����Ӧ�ļ������ᱻ����ִ�С�

### ��������

1. **ע���¼�������**�������ڳ�ʼ���׶Σ��Ὣ�¼�������ע�ᵽ�¼�Դ�ϣ�ָ��Ҫ�������¼����͡�
2. **�¼�ѭ��**���������һ������ѭ�������ϵȴ��¼��ķ�����
3. **�¼�����**�����¼�Դ��⵽�ض��¼�����ʱ���ᴴ��һ���¼����󣬲����䴫�ݸ��¼�ѭ����
4. **�¼��ַ�**���¼�ѭ�����յ��¼��󣬻�����¼����ͺ�ע����Ϣ�����¼��ַ�����Ӧ���¼���������
5. **�¼�����**���¼����������յ��¼���ִ����Ӧ�Ĵ����߼���

### �ŵ�

- **�߲�����������**������ͬʱ�������¼���������ҪΪÿ���¼�����һ���������̣߳��Ӷ������ϵͳ�Ĳ������ܡ�
- **��Ӧ�Ժ�**���ܹ���ʱ��Ӧ�û��Ĳ������ⲿ�¼��������û����顣
- **����չ��ǿ**�����Է��������µ��¼����ͺ��¼��������������㲻ͬ��ҵ������


## Reactorģ��

### �������

- **Handle�������**���������Ƕ���Դ�ĳ����������˿��Բ����¼�����Դ���������׽��֡��ļ��������ȡ���Щ��Դ��ϵͳ���ܹ������ض��� I/O �¼��������׽����ϵ����ݵ���ļ���д�ȡ�
- **Synchronous Event Demultiplexer��ͬ���¼���·��������**������ Reactor ģ�͵Ĺؼ����֮һ������������ Handle �ϵ��¼�����ĳ�� Handle �����¼�����ʱ�����Ὣ��Щ�¼�֪ͨ�� Reactor���ڲ�ͬ�Ĳ���ϵͳ�У�ͬ���¼���·�������в�ͬ��ʵ�֣������� Linux ϵͳ�У����õ���`select`��`poll`��`epoll`������
- **Reactor����Ӧ����**����Ϊ�¼��ķַ����ģ�Reactor �������ͬ���¼���·�������������¼����������¼����ͽ���ַ�����Ӧ���¼��������Event Handler����
- **Event Handler���¼��������**���Ǵ�������¼��Ĵ���ģ�飬ÿ���¼���������Ӧһ���ض����͵��¼����� Reactor ���¼��ַ�����ʱ������ִ����Ӧ�Ĵ����߼��������ȡ�������ݡ�����ҵ���߼��ȡ�

### ��������

1. **ע���¼�**������������ʱ���Ὣ��Ҫ������ Handle �Ͷ�Ӧ���¼��������ע�ᵽ Reactor �С�
2. **�¼�����**��Reactor ͨ��ͬ���¼���·���������ϼ�����Щ Handle �ϵ��¼���
3. **�¼��ַ�**�������¼�����ʱ��ͬ���¼���·�������Ὣ�¼�֪ͨ�� Reactor��Reactor �����¼����ͽ���ַ�����Ӧ���¼��������
4. **�¼�����**���¼����������յ��¼���ִ����Ӧ�Ĵ����߼���������ɺ�Reactor ���������¼����γ�һ��ѭ����

### ��������

- **���߳� Reactor ģ��**��������ģ���У�Reactor �����е��¼��������������ͬһ���߳��С������ŵ���ʵ�ּ򵥣������ڴ��������������ӵĳ�����������������������ʱ���������в�������һ���߳���ִ�У����ܻᵼ������ƿ����
- **���߳� Reactor ģ��**��Ϊ�˿˷����߳� Reactor ģ�͵��������⣬���߳� Reactor ģ���������̳߳ء�������ģ���У�Reactor ��Ȼ�����¼��ļ����ͷַ������¼��������ύ���̳߳��е��߳���ִ�С��������Գ�����ö�� CPU ����Դ�����ϵͳ�Ĳ�������������
- **���� Reactor ģ��**������ Reactor ģ�ͽ�һ���Ż��˶��߳� Reactor ģ�͡����� Reactor ��Ϊ�� Reactor �ʹ� Reactor �����֡��� Reactor ��������ͻ��˵��������󣬲����µ����ӷ������ Reactor���� Reactor ��������Щ�����ϵĶ�д�¼�������ģ�Ϳ��Ը��õ�Ӧ�Դ����������ӵĳ��������ϵͳ�Ŀ���չ�Ժ����ܡ�

### �ŵ�

- **�߲�����������**��ͨ���¼������Ͷ�·���ü������ܹ�ͬʱ�������ͻ��˵��������ϵͳ�Ĳ������ܡ�
- **����չ��ǿ**�����Ը���ϵͳ���������ص����̳߳صĴ�С�� Reactor ������������Ӧ��ͬ�Ĳ���������
- **��Ӧ�ٶȿ�**���ܹ���ʱ��Ӧ�û������󣬼����û��ĵȴ�ʱ�䡣

## Pr



## �ڴ�ػ�����


### �ڴ�����ԭ��
- �ڴ�����:ͨ�������ڴ棬����Ƶ���ķ���ͻ��ղ����������ڴ���Ƭ����������ѹ����
- �ּ�����:�ڴ�鰴��С�ּ����й�����С���ڴ������С�ڴ����䣬�����Ӵ��ڴ����䡣
- �̱߳��ػ���(Thread-LocalCache):ÿ���̶߳����Լ����ڴ滺��(��ThreadLocalʵ��)���Ӷ����ٶ�
�߳̾�������߷���Ч�ʡ�

### ���Ŀ��

- **�����ڴ���Ƭ**��Ƶ���ؽ����ڴ������ͷŻᵼ���ڴ���Ƭ����ʹ�ÿ����ڴ�ռ��ò�������Ӱ���ڴ�ʹ��Ч�ʡ�Netty ���ڴ�ػ�����ͨ�������ѷ�����ڴ�飬�������ڴ���Ƭ�Ĳ�����
- **�����ڴ����ͻ��տ���**���ڴ�ķ���ͻ��ղ���ͨ���ȽϺ�ʱ���������ڸ߲��������£�Ƶ�����ڴ�������Ϊ����ƿ�����ڴ�ػ�����Ԥ�ȷ���һ����ڴ棬Ȼ���仮��Ϊ���С�飬����Ҫʹ���ڴ�ʱ��ֱ�Ӵ��ڴ���л�ȡ��ʹ����Ϻ��ٹ黹���ڴ�أ�������Ƶ����ϵͳ���ã��Ӷ���������ܡ�

### �������

- **PoolArena**���ڴ�صĺ��Ĺ������������������ڴ��ķ���ͻ��ա�ÿ��`PoolArena`��ά�����`PoolChunkList`��`PoolSubpage`�����ڴ洢��ͬ��С���ڴ�顣
- **PoolChunk**����ʾһ����������ڴ�����ͨ����`8MB`��`PoolChunk`�ᱻ����Ϊ���С�飬������Ҫ�������ͬ������
- **PoolSubpage**�����ڹ���С���ڴ棨С��`8KB`���ķ��䡣����Ҫ����С���ڴ�ʱ��`PoolArena`���`PoolChunk`�л��ֳ�һ��`PoolSubpage`���������Ͻ����ڴ���䡣
- **PoolThreadCache**��ÿ���̶߳���һ��������`PoolThreadCache`�����ڻ�����߳�Ƶ��ʹ�õ��ڴ�顣���߳���Ҫ�ڴ�ʱ�����Ȼ��`PoolThreadCache`�в��ң�����ҵ���ֱ��ʹ�ã���������`PoolArena`�ľ�����������ڴ������ٶȡ�

### �ڴ��������

1. **���Դ��̻߳����ȡ**����һ���߳���Ҫ�����ڴ�ʱ�����Ȼ᳢�Դ����Ӧ��`PoolThreadCache`�л�ȡ���ʵ��ڴ�顣����������п��õ��ڴ�飬��ֱ�ӷ��أ���һ�������ǳ����٣���Ϊ����Ҫ������������
2. **�� PoolArena ����**�����`PoolThreadCache`��û�п��õ��ڴ�飬����`PoolArena`�н��з��䡣`PoolArena`�����������ڴ��С��ѡ����ʵ�`PoolChunk`��`PoolSubpage`���з��䡣
3. **�ڴ�黮��**�����`PoolArena`��û�к��ʴ�С���ڴ�飬���`PoolChunk`�л��ֳ�һ���µ��ڴ�顣����С���ڴ棬��ʹ��`PoolSubpage`���й���
4. **����ʣ���ڴ��**����������`PoolChunk`��`PoolSubpage`�л���ʣ��Ŀ����ڴ�飬�Ὣ�仺�浽`PoolThreadCache`�У��Ա����ʹ�á�

### �ڴ��������

1. **�黹���̻߳���**����һ���ڴ��ʹ����Ϻ󣬻����ȳ��Խ���黹����Ӧ��`PoolThreadCache`�С����`PoolThreadCache`���пռ䣬���ڴ�黺���������ȴ��������á�
2. **�黹�� PoolArena**�����`PoolThreadCache`���������ڴ��黹��`PoolArena`�С�`PoolArena`�Ὣ����Ϊ���ã��Ա�������䡣
3. **�ϲ������ڴ��**��`PoolArena`�ᶨ�ڼ��`PoolChunk`��`PoolSubpage`�������ڵĿ����ڴ��ϲ��ɸ�����ڴ�飬�����ڴ���Ƭ��

### �ŵ�

- **������**��ͨ�������ڴ����ͻ��յĿ������Լ������ڴ���Ƭ�����������ϵͳ�����ܣ������ڸ߲��������±��ֳ�ɫ��
- **�ڴ������ʸ�**���ڴ�ػ������ܹ���������ѷ�����ڴ棬�������ڴ���˷ѣ�������ڴ������ʡ�
- **�̰߳�ȫ**��Netty ���ڴ�ػ�����������Ͽ������̰߳�ȫ���⣬ͨ��`PoolThreadCache`�������ƣ�ȷ���˶��̻߳����µ��ڴ����ͻ��ղ�������ȷ�ԡ�

## �㿽������


### �㿽������

��ͳ�����ݴ�������У�����������Ҫ���û��ռ���ں˿ռ�֮���θ��ƣ�������Ĵ����� CPU ��Դ��ʱ�䡣���㿽���������Ǿ������ٻ������Щ����Ҫ�ĸ��Ʋ�����������ֱ�ӴӴ��̻������豸���䵽Ŀ��λ�á�

### Netty �㿽����ʵ�ַ�ʽ

#### ���� CompositeByteBuf ���㿽��

CompositeByteBuf ���Խ���� ByteBuf �ϲ���һ���߼��ϵ� ByteBuf�������轫��Щ ByteBuf �����ݽ���ʵ�ʵĸ��ơ����磬�ڴ��������ݰ�ʱ������Խ����Ƿֱ��װ�� ByteBuf��Ȼ��ʹ�� CompositeByteBuf ��������������������ں����Ĵ�������У��Ϳ�������һ�������� ByteBuf һ��������Щ���ݣ�������Ҫ�����ǵ����ݸ��Ƶ�һ���µ� ByteBuf �С�
ʾ���������£�



java











```java
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

public class CompositeByteBufExample {
    public static void main(String[] args) {
        ByteBuf buffer1 = Unpooled.buffer(10);
        buffer1.writeBytes("Hello".getBytes());

        ByteBuf buffer2 = Unpooled.buffer(10);
        buffer2.writeBytes(" World".getBytes());

        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
        compositeByteBuf.addComponents(true, buffer1, buffer2);

        System.out.println(compositeByteBuf.toString(io.netty.util.CharsetUtil.UTF_8));
    }
}
```

#### ���� FileRegion ���㿽��

����Ҫ���ļ����ݷ��͵�����ʱ��Netty �� FileRegion ����ʵ���㿽�����䡣�������˲���ϵͳ�ײ���㿽�����ƣ��� Linux ��`sendfile()`ϵͳ���ã�ֱ�ӽ��ļ����ݴӴ��̴��䵽�����׽��֣��������������û��ռ���ں˿ռ�֮��Ķ�θ��ơ�
ʾ���������£�



java











```java
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.io.File;
import java.io.RandomAccessFile;

public class FileRegionServer {
    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
              .channel(NioServerSocketChannel.class)
              .childHandler(new ChannelInitializer<SocketChannel>() {
                  @Override
                  public void initChannel(SocketChannel ch) throws Exception {
                      ch.pipeline().addLast(
                              new LineBasedFrameDecoder(8192),
                              new StringDecoder(),
                              new StringEncoder(),
                              new ChunkedWriteHandler(),
                              new FileRegionServerHandler()
                      );
                  }
              });

            ChannelFuture f = b.bind(PORT).sync();
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

class FileRegionServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RandomAccessFile file = new RandomAccessFile(new File("example.txt"), "r");
        FileRegion region = new DefaultFileRegion(file.getChannel(), 0, file.length());
        ctx.write(region);
        ctx.writeAndFlush("\r\n");
        file.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
```

#### ���� ByteBuf.slice () �� duplicate () ���㿽��

ByteBuf ��`slice()`�������Դ���һ���µ� ByteBuf������ԭʼ ByteBuf ����ײ���ڴ����ݣ�ֻ����ͼ��ͬ��`duplicate()`����Ҳ���ƣ����ᴴ��һ���µ� ByteBuf ʵ������ԭʼ ByteBuf �����ڴ����ݡ���������Ҫ�����ݽ��в��ִ������ʹ��ʱ���Ϳ��Ա������ݵĸ��ơ�
ʾ���������£�



java











```java
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufSliceExample {
    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(10);
        buffer.writeBytes("HelloWorld".getBytes());

        ByteBuf sliced = buffer.slice(0, 5);
        System.out.println(sliced.toString(io.netty.util.CharsetUtil.UTF_8));
    }
}
```





### �㿽������������

- **���� CPU ����**�����ڼ��������ݸ��Ʋ�����CPU ���Խ������ʱ�����ڴ���ҵ���߼��������ǽ����ڴ渴�ƣ��Ӷ������ϵͳ���������ܡ�
- **������ݴ���Ч��**���㿽�������������������ڴ�֮��Ķ�θ��ƣ����������ݴ�����ӳ٣���������ݴ�����ٶȡ�
- **�����ڴ�ռ��**��ͨ�������ڴ����ݣ������˲���Ҫ���ڴ����͸��ƣ�������ϵͳ���ڴ�ռ�á�
## ��Ч���̹߳���

Netty �߱���Ч���̹߳�����ƣ�������������ϵͳ�Ĳ����������������ܣ�������߳�ģ�͡��̳߳ص�ʹ�õȷ���Ϊ����ϸ���ܣ�

### �߳�ģ��

Netty ��Ҫ�������߳�ģ�ͣ��ֱ��ǵ��߳�ģ�͡����߳�ģ�ͺ����Ӷ��߳�ģ�ͣ�����Ը���ʵ�ʳ�����ѡ����ʵ��߳�ģ�͡�



- **���߳�ģ��**���ڵ��߳�ģ���ֻ��һ���̸߳��������е� I/O �������������ӵĽ��ܡ���д�ȡ�����ģ��ʵ�������򵥣����������ڴ��������������ӵĳ�������Ϊ�����̵߳Ĵ����������ޣ�����������������ʱ�����׳�Ϊ����ƿ����
- **���߳�ģ��**�����߳�ģ���������̳߳أ���һ��ר�ŵ��̸߳�����ܿͻ��˵����ӣ������ӽ�����Ķ�д���������̳߳��еĶ���߳��������������Գ�����ö�� CPU ����Դ������ϵͳ�Ĳ�������������
- **���Ӷ��߳�ģ��**�����Ӷ��߳�ģ���ǶԶ��߳�ģ�͵Ľ�һ���Ż����������� Reactor �̳߳غʹ� Reactor �̳߳أ��� Reactor �̳߳ظ�����ܿͻ��˵��������󣬲����µ����ӷ������ Reactor �̳߳أ��� Reactor �̳߳ظ�������Щ�����ϵĶ�д�¼�������ģ���ܹ����õ�Ӧ�Դ����������ӵĳ��������ϵͳ�Ŀ���չ�Ժ����ܡ�

### �̳߳ص�ʹ��

Netty ʹ��`EventLoopGroup`��Ϊ�̳߳أ�����`EventLoop`�ļ��ϡ�`EventLoop`��������һ�����̵߳�ִ������������һ������`Channel`�� I/O �������¼���



- **���� EventLoopGroup**�������� Netty ��������ͻ���ʱ����Ҫ����`EventLoopGroup`ʵ�������磬�ڴ���������ʱ�����Դ���һ����`EventLoopGroup`��һ����`EventLoopGroup`��



java











```java
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        // ������EventLoopGroup��ͨ�����ڽ��ܿͻ�������
        EventLoopGroup bossGroup = new NioEventLoopGroup(); 
        // ������EventLoopGroup�����ڴ���ͻ������ӵĶ�д����
        EventLoopGroup workerGroup = new NioEventLoopGroup(); 
        try {
            ServerBootstrap b = new ServerBootstrap(); 
            b.group(bossGroup, workerGroup)
              .channel(NioServerSocketChannel.class) 
              .childHandler(new ChannelInitializer<SocketChannel>() { 
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        // ��ʼ��Channel�Ĵ����߼�
                    }
                })
              .option(ChannelOption.SO_BACKLOG, 128)          
              .childOption(ChannelOption.SO_KEEPALIVE, true); 

            ChannelFuture f = b.bind(PORT).sync(); 
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
```



- **�̷߳���**��`EventLoopGroup`��Ϊÿ��`Channel`����һ��`EventLoop`��ͬһ��`Channel`������ I/O ��������ͬһ��`EventLoop`�������������Ա�����̲߳�������`Channel`�������̰߳�ȫ���⡣

### �������

Netty ��`EventLoop`��֧��������ȹ��ܣ�������ָ����ʱ����ӳٺ�ִ�����������ʵ�ֶ�ʱ�����������ȹ��ܷǳ����á�



java











```java
import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.concurrent.TimeUnit;

public class TaskSchedulingExample {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoop eventLoop = group.next();

        // �ӳ�1���ִ������
        eventLoop.schedule(() -> {
            System.out.println("Delayed task executed.");
        }, 1, TimeUnit.SECONDS);

        // ÿ��2��ִ��һ������
        eventLoop.scheduleAtFixedRate(() -> {
            System.out.println("Periodic task executed.");
        }, 0, 2, TimeUnit.SECONDS);
    }
}
```

### ����

- **�߲�����������**��ͨ��������߳�ģ�ͺ��̳߳ص�ʹ�ã�Netty �ܹ�������ö�� CPU ����Դ��ͬʱ��������Ĳ������Ӻ�����
- **�̰߳�ȫ**��ÿ��`Channel`�� I/O ��������ͬһ��`EventLoop`���������˶��̲߳�������`Channel`�������̰߳�ȫ���⣬���˱��ģ�͡�
- **����������**��֧��������ȹ��ܣ����Է����ʵ�ֶ�ʱ������������������㲻ͬ��ҵ������


## ��Ч��Pipeline���� 
### ����

Pipeline ��һ�� ChannelHandler ������ÿ�� Channel ����һ����֮������ Pipeline���� Channel ���� I/O �¼�����ʱ����Щ�¼����� Pipeline �����������ξ��� Pipeline �еĸ��� ChannelHandler ���д���ͨ�� Pipeline ���ƣ�Netty ���Խ���ͬ�Ĵ����߼���װ�ڲ�ͬ�� ChannelHandler �У�ʵ�ִ����ģ�黯�͸��á�

### �ṹ

- ChannelHandler

  ���� Pipeline �еĻ�������Ԫ�������Դ��� I/O �¼���ת�����ݵȡ�ChannelHandler ��Ϊ�������ͣ�ChannelInboundHandler �� ChannelOutboundHandler��

    - **ChannelInboundHandler**�����ڴ�����վ�¼������ȡ���ݡ����ӽ����ȡ�������վ�¼�����ʱ���¼���� Pipeline ��ͷ����ʼ�����ξ������� ChannelInboundHandler ���д���
    - **ChannelOutboundHandler**�����ڴ����վ�¼����緢�����ݡ��ر����ӵȡ����г�վ�¼�����ʱ���¼���� Pipeline ��β����ʼ�����ξ������� ChannelOutboundHandler ���д���

- **ChannelHandlerContext**��ÿ�� ChannelHandler ����һ����֮������ ChannelHandlerContext���������� ChannelHandler �� Pipeline �е���������Ϣ��ͨ�� ChannelHandlerContext��ChannelHandler ������ Pipeline �е����� Handler ���н������罫�¼����ݸ���һ�� Handler�������µ��¼��ȡ�

### ��������

#### ��վ�¼���������

1. �� Channel ������վ�¼�������յ��µ����ݣ�����ʱ���¼������ȵ��� Pipeline ��ͷ����
2. ͷ���� ChannelInboundHandler ����յ��¼�����������д���������ɺ�������ѡ���¼����ݸ���һ�� ChannelInboundHandler��������ֹ�¼��Ĵ�����
3. �¼������ξ��� Pipeline �еĸ��� ChannelInboundHandler��ֱ����������ߵ��� Pipeline ��β����

#### ��վ�¼���������

1. ����Ҫִ�г�վ�������緢�����ݣ�ʱ��������� Pipeline ��β����ʼ��
2. β���� ChannelOutboundHandler ����յ��������󣬲�������д���������ɺ�������ѡ�񽫲������ݸ���һ�� ChannelOutboundHandler������ֱ��ִ�в�����
3. ���������ξ��� Pipeline �еĸ��� ChannelOutboundHandler��ֱ����ִ�л��ߵ��� Pipeline ��ͷ����

### ʾ������

java











```java
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyPipelineExample {
    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
              .channel(NioServerSocketChannel.class)
              .childHandler(new ChannelInitializer<SocketChannel>() {
                  @Override
                  public void initChannel(SocketChannel ch) throws Exception {
                      ChannelPipeline pipeline = ch.pipeline();
                      // ��ӽ�����
                      pipeline.addLast(new StringDecoder()); 
                      // ����Զ��崦����
                      pipeline.addLast(new CustomInboundHandler()); 
                      // ��ӱ�����
                      pipeline.addLast(new StringEncoder()); 
                      // ����Զ����վ������
                      pipeline.addLast(new CustomOutboundHandler()); 
                  }
              });

            ChannelFuture f = b.bind(PORT).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}

class CustomInboundHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = (String) msg;
        System.out.println("Received message: " + message);
        // ����Ϣ���ݸ���һ��Handler
        ctx.fireChannelRead(msg); 
    }
}

class CustomOutboundHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        String message = (String) msg;
        System.out.println("Sending message: " + message);
        // ����ִ�г�վ����
        ctx.write(msg, promise); 
    }
}
```

### ����

- **ģ�黯���**��ͨ������ͬ�Ĵ����߼���װ�ڲ�ͬ�� ChannelHandler �У�Pipeline ����ʵ���˴����ģ�黯������˴���Ŀ�ά���Ժ͸����ԡ�
- **�����**�����Ը�����Ҫ��̬����ӡ�ɾ�����滻 Pipeline �е� ChannelHandler�������㲻ͬ��ҵ������
- **�¼�����**��Pipeline ���ƻ����¼��������ܹ���Ч�ش������ I/O �¼��������ϵͳ�Ĳ�������������
