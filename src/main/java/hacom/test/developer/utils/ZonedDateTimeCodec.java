package hacom.test.developer.utils;

import java.time.ZonedDateTime;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class ZonedDateTimeCodec implements Codec<ZonedDateTime>{

	public ZonedDateTimeCodec() {
		// TODO Auto-generated constructor stub
	}

    @Override
    public void encode(final BsonWriter writer, final ZonedDateTime value, final EncoderContext encoderContext) {
        writer.writeString(value.toString());
    }
 
    @Override
    public ZonedDateTime decode(final BsonReader reader, final DecoderContext decoderContext) {
        return ZonedDateTime.parse(reader.readString());
    }
 
    @Override
    public Class<ZonedDateTime> getEncoderClass() {
        return ZonedDateTime.class;
    }

}
