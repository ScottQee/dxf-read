**读取DXF**
通用读取使用dxf.service.DxfMultiRead

`
   File f = new File(path);
`

`
       DxfMultiRead example = new DxfMultiRead(f);
`

初始化example对象后，example对象中含有多个list属性储存着dxf中的各种信息比如layer（图层） lwPolyline（多段线）等信息，请自行挑选

写DXF

使用dxf.Document模板写出

`
    Document document = new Document();
`

 使用 
 
 `.addLayer(Layer layer)` `.addEntity(Entity e)` `.addLType(LType lType)` 
 
 添加图层，图形实体，线型要素
 
 Entity实体已实现Circle, Arc, Line, LwPolyline, Point, Text子类的输出
 
 之后，使用
 
 `document.write(String path)` 
 
 将document输出为dxf文件到指定path