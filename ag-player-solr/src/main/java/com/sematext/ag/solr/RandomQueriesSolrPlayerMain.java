/**
 * Copyright Sematext International
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sematext.ag.solr;

import com.sematext.ag.PlayerConfig;
import com.sematext.ag.PlayerRunner;
import com.sematext.ag.solr.sink.SimpleQuerySolrSink;
import com.sematext.ag.source.SimpleSourceFactory;
import com.sematext.ag.source.random.SearchRandomNumberEventSource;

/**
 * Class used for starting {@link SimpleQuerySolrSink} with random queries.
 * 
 * @author sematext, http://www.sematext.com/
 */
public final class RandomQueriesSolrPlayerMain {
  private RandomQueriesSolrPlayerMain() {
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("Usage: solrUrl searchFieldName eventsCount");
      System.out.println("Example: http://localhost:8983/solr/core/select text 10000");
      System.exit(1);
    }

    String solrUrl = args[0];
    String searchFieldName = args[1];
    String eventsCount = args[2];

    PlayerConfig config = new PlayerConfig(SimpleSourceFactory.SOURCE_CLASS_CONFIG_KEY,
        SearchRandomNumberEventSource.class.getName(), SearchRandomNumberEventSource.SEARCH_FIELD_NAME_KEY,
        searchFieldName, SearchRandomNumberEventSource.MAX_EVENTS_KEY, eventsCount, PlayerRunner.SINK_CLASS_CONFIG_KEY,
        SimpleQuerySolrSink.class.getName(), SimpleQuerySolrSink.SOLR_URL_KEY, solrUrl);
    PlayerRunner.play(config);
  }
}
