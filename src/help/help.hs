<?xml version="1.0" encoding='ISO-8859-1' ?>
<!DOCTYPE helpset
PUBLIC "-//Sun Microsystems Inc.//DTD JavaHelp HelpSet Version 1.0//EN"
"http://java.sun.com/products/javahelp/helpset_1_0.dtd">
<helpset version="1.0">

   <title>Proyecto fin de ciclo - Ayuda</title>
   
   <maps> 
      <homeID>manual</homeID>
      <mapref location="map.jhm"/>
   </maps>

   <view>
      <name>TOC</name>
      <label>Tabla de contenidos</label>
      <type>javax.help.TOCView</type>
      <data>toc.xml</data>
   </view>

   <view>
      <name>Indice</name>
      <label>Índice</label>
      <type>javax.help.IndexView</type>
      <data>index.xml</data>
   </view>

	<view>
		<name>Favoritos </name>
		<label> Favoritos</label>
		<type> javax.help.FavoritesView</type>
	</view>

   <view>
      <name>Buscar</name>
      <label>Buscar</label>
      <type>javax.help.SearchView</type>
      <data engine="com.sun.java.help.search.DefaultSearchEngine">
         JavaHelpSearch
      </data>
   </view>
   
   <presentation default=true>
		<name>main window</name>
		<size width="700" height="600" />
		<location x="200" y="200" />
		<title>Proyecto fin de ciclo - Ayuda</title>
		<toolbar>
			<helpaction>javax.help.BackAction</helpaction>
			<helpaction>javax.help.ForwardAction</helpaction>
			<helpaction image="homeicon">javax.help.HomeAction</helpaction>
			<helpaction>javax.help.FavoritesAction</helpaction>
		</toolbar>
	</presentation>

</helpset>