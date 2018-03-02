package swan.biz.readhub.network

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

/**
 * Created by stephen on 18-2-11.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
interface IAtomResponse: Serializable