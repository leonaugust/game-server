package server.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import server.domain.TopItem;

@Service
public class TopDao {

  @Resource
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public List<TopItem> getUsersSortedByRating(int quantity) {
    return namedParameterJdbcTemplate.query(
        "SELECT id, name, rating FROM user_profile ORDER BY rating DESC LIMIT :quantity",
        Map.of("quantity", quantity),
        (res, rowNum) -> new TopItem(
            res.getInt("id"),
            res.getString("name"),
            res.getInt("rating")
        )
    );

  }


}
