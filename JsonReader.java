public static void processJson() throws IOException {
        List<Object[]> output = new LinkedList<Object[]>();
        File file = ResourceUtils.getFile("classpath:input.json");
        String content = new String(Files.readAllBytes(file.toPath()));
        JsonObject jsonObject = new JsonParser().parse(content).getAsJsonObject();
        JsonArray jsonArray = jsonObject.get("ResultSet").getAsJsonObject().get("Rows").getAsJsonArray();
        for (JsonElement obj : jsonArray) {
           JsonArray dataArr =  obj.getAsJsonObject().get("Data").getAsJsonArray();
           Object[] record = new Object[dataArr.size()];
           for (int i =0 ; i< dataArr.size(); i++ ){
               JsonElement element = dataArr.get(i);
               for (String key : element.getAsJsonObject().keySet()){
                   JsonObject object = element.getAsJsonObject();
                   record[i] = object.get(key);
               }
           }
           output.add(record);
        }
        System.out.println(output.size());
    }
