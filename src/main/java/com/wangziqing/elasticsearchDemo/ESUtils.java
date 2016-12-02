//package com.wangziqing.elasticsearchDemo;
//import java.util.Map;
//import java.util.Objects;
//
///**
// * Created by 王梓青 on 2016/11/29.
// */
//public class ESUtils {
//    private static final String HOST = "localhost";
//    private static final int PORT = 9300;
//    //集群名字
//    private static final String CLUSTER_NAME="elasticsearch";
//    private static Client client;
//    static {
//        client=getClinet(2);
//    }
//    /**
//     * 1.创建一个嵌套的节点，充当集群的一个节点
//     * 从这个嵌套的节点请求一个Client
//     * 2.创建一个TransportClient来连接集群。
//     *  TransportClient利用transport模块远程连接一个elasticsearch集群。
//     *  它并不加入到集群中，只是简单的获得一个或者多个初始化的transport地址，并以轮询的方式与这些地址进行通信。
//     * @return
//     */
//    public static Client getClinet(int type) {
//        if(type==1){
//            //设置node.data为false或者设置 node.client为true,不保留数据
////            Node node = nodeBuilder().node();
////            Client client = node.client(true);
////            node.close();
//        }else if(type==2){
//            //嗅到集群的其它部分，并将它们加入到机器列表
//            Settings settings = ImmutableSettings.settingsBuilder()
//                    .put("client.transport.sniff", true).build();
//            Client client = new TransportClient()
//                    .addTransportAddress(new InetSocketTransportAddress(HOST, PORT));
//        }
//    }
//
//    /**
//     *  关系数据库     ⇒ 数据库 ⇒ 表    ⇒ 行    ⇒ 列(Columns)
//     *  Elasticsearch  ⇒ 索引   ⇒ 类型  ⇒ 文档  ⇒ 字段(Fields)
//     * @param index 索引
//     * @param type 类型
//     * @param id  id
//     * @param json 文档
//     * @return IndexResponse
//     * IndexResponse将会提供给你索引信息
//     * // Index name
//     * String _index = response.getIndex();
//     * // Type name
//     * String _type = response.getType();
//     * // Document ID (generated or not)
//     * String _id = response.getId();
//     * // Version (if it's the first time you index this document, you will get: 1)
//     * long _version = response.getVersion();
//     */
//    public static IndexResponse indexData(String index,String type,String id,Map<String,Object> json){
//        if(Objects.nonNull(id)){
//            return IndexResponse response = client.prepareIndex(index, type,id)
//                    .setSource(json)
//                    .execute()
//                    .actionGet();
//        }else{
//            return IndexResponse response = client.prepareIndex(index, type)
//                    .setSource(json)
//                    .execute()
//                    .actionGet();
//        }
//    }
//
//    public static GetResponse getById(String index,String type,String id){
//        return  client.prepareGet(index, type,id)
//                .execute()
//                .actionGet();
//    }
//    public static GetResponse deleteById(String index,String type,String id){
//        return  client.prepareDelete(index, type,id)
//                .execute()
//                .actionGet();
//    }
//    public static IndexResponse updateData(String index,String type,String id,Map<String,Object> json){
//        //不存在则使用indexRequest插入，存在则更新
//        IndexRequest indexRequest = new IndexRequest(index, type,id)
//                .source(json);
//        UpdateRequest updateRequest = new UpdateRequest(index, type,id)
//                .doc(json)
//                .upsert(indexRequest);
//        return client.update(updateRequest).get();
//    }
//    public static void batchIndexOrDelete(){
//        BulkRequestBuilder bulkRequest = client.prepareBulk();
//
//// either use client#prepare, or use Requests# to directly build index/delete requests
//        bulkRequest.add(client.prepareIndex("twitter", "tweet", "1")
//                .setSource(jsonBuilder()
//                        .startObject()
//                        .field("user", "kimchy")
//                        .field("postDate", new Date())
//                        .field("message", "trying out Elasticsearch")
//                        .endObject()
//                )
//        );
//
//        bulkRequest.add(client.prepareIndex("twitter", "tweet", "2")
//                .setSource(jsonBuilder()
//                        .startObject()
//                        .field("user", "kimchy")
//                        .field("postDate", new Date())
//                        .field("message", "another post")
//                        .endObject()
//                )
//        );
//
//        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
//        if (bulkResponse.hasFailures()) {
//            // process failures by iterating through each bulk response item
//        }
//    }
//}
