/**
 * Copyright (c) 2015-present, Facebook, Inc. All rights reserved.
 * <p>
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to
 * use, copy, modify, and distribute this software in source code or binary
 * form for use in connection with the web services and APIs provided by
 * Facebook.
 * <p>
 * As with any software that integrates with the Facebook platform, your use
 * of this software is subject to the Facebook Developer Principles and
 * Policies [http://developers.facebook.com/policy/]. This copyright notice
 * shall be included in all copies or substantial portions of the software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package com.facebook.ads.sdk;

import com.facebook.ads.sdk.APIException.MalformedResponseException;
import com.google.gson.*;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * This class is auto-genereated.
 *
 * For any issues or feature requests related to this class, please let us know
 * on github and we'll fix in our codegen framework. We'll not be able to accept
 * pull request for this class.
 *
 */
public class LeadGenQualifier extends APINode {
    @SerializedName("category")
    private String mCategory = null;
    @SerializedName("field_key")
    private String mFieldKey = null;
    @SerializedName("id")
    private String mId = null;
    @SerializedName("label")
    private String mLabel = null;
    @SerializedName("question")
    private String mQuestion = null;
    protected static Gson gson = null;

    public LeadGenQualifier() {
    }

    public String getId() {
        return getFieldId().toString();
    }

    public static LeadGenQualifier loadJSON(String json, APIContext context) {
        LeadGenQualifier leadGenQualifier = getGson().fromJson(json, LeadGenQualifier.class);
        if (context.isDebug()) {
            JsonParser parser = new JsonParser();
            JsonElement o1 = parser.parse(json);
            JsonElement o2 = parser.parse(leadGenQualifier.toString());
            if (o1.getAsJsonObject().get("__fb_trace_id__") != null) {
                o2.getAsJsonObject().add("__fb_trace_id__", o1.getAsJsonObject().get("__fb_trace_id__"));
            }
            if (!o1.equals(o2)) {
                context.log("[Warning] When parsing response, object is not consistent with JSON:");
                context.log("[JSON]" + o1);
                context.log("[Object]" + o2);
            }
            ;
        }
        leadGenQualifier.context = context;
        leadGenQualifier.rawValue = json;
        return leadGenQualifier;
    }

    public static APINodeList<LeadGenQualifier> parseResponse(String json, APIContext context, APIRequest request) throws MalformedResponseException {
        APINodeList<LeadGenQualifier> leadGenQualifiers = new APINodeList<LeadGenQualifier>(request, json);
        JsonArray arr;
        JsonObject obj;
        JsonParser parser = new JsonParser();
        Exception exception = null;
        try {
            JsonElement result = parser.parse(json);
            if (result.isJsonArray()) {
                // First, check if it's a pure JSON Array
                arr = result.getAsJsonArray();
                for (int i = 0; i < arr.size(); i++) {
                    leadGenQualifiers.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context));
                }
                ;
                return leadGenQualifiers;
            } else if (result.isJsonObject()) {
                obj = result.getAsJsonObject();
                if (obj.has("data")) {
                    if (obj.has("paging")) {
                        JsonObject paging = obj.get("paging").getAsJsonObject();


                        leadGenQualifiers.setPaging(paging);
                    }
                    if (obj.get("data").isJsonArray()) {
                        // Second, check if it's a JSON array with "data"
                        arr = obj.get("data").getAsJsonArray();
                        for (int i = 0; i < arr.size(); i++) {
                            leadGenQualifiers.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context));
                        }
                        ;
                    } else if (obj.get("data").isJsonObject()) {
                        // Third, check if it's a JSON object with "data"
                        obj = obj.get("data").getAsJsonObject();
                        boolean isRedownload = false;
                        for (String s : new String[]{"campaigns", "adsets", "ads"}) {
                            if (obj.has(s)) {
                                isRedownload = true;
                                obj = obj.getAsJsonObject(s);
                                for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                                    leadGenQualifiers.add(loadJSON(entry.getValue().toString(), context));
                                }
                                break;
                            }
                        }
                        if (!isRedownload) {
                            leadGenQualifiers.add(loadJSON(obj.toString(), context));
                        }
                    }
                    return leadGenQualifiers;
                } else if (obj.has("images")) {
                    // Fourth, check if it's a map of image objects
                    obj = obj.get("images").getAsJsonObject();
                    for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                        leadGenQualifiers.add(loadJSON(entry.getValue().toString(), context));
                    }
                    return leadGenQualifiers;
                } else {
                    // Fifth, check if it's an array of objects indexed by id
                    boolean isIdIndexedArray = true;
                    for (Map.Entry entry : obj.entrySet()) {
                        String key = (String) entry.getKey();
                        if (key.equals("__fb_trace_id__")) {
                            continue;
                        }
                        JsonElement value = (JsonElement) entry.getValue();
                        if (
                                value != null &&
                                        value.isJsonObject() &&
                                        value.getAsJsonObject().has("id") &&
                                        value.getAsJsonObject().get("id") != null &&
                                        value.getAsJsonObject().get("id").getAsString().equals(key)
                                ) {
                            leadGenQualifiers.add(loadJSON(value.toString(), context));
                        } else {
                            isIdIndexedArray = false;
                            break;
                        }
                    }
                    if (isIdIndexedArray) {
                        return leadGenQualifiers;
                    }

                    // Sixth, check if it's pure JsonObject
                    leadGenQualifiers.clear();
                    leadGenQualifiers.add(loadJSON(json, context));
                    return leadGenQualifiers;
                }
            }
        } catch (Exception e) {
            exception = e;
        }
        throw new MalformedResponseException(
                "Invalid response string: " + json,
                exception
        );
    }

    @Override
    public APIContext getContext() {
        return context;
    }

    @Override
    public void setContext(APIContext context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return getGson().toJson(this);
    }


    public String getFieldCategory() {
        return mCategory;
    }

    public LeadGenQualifier setFieldCategory(String value) {
        this.mCategory = value;
        return this;
    }

    public String getFieldFieldKey() {
        return mFieldKey;
    }

    public LeadGenQualifier setFieldFieldKey(String value) {
        this.mFieldKey = value;
        return this;
    }

    public String getFieldId() {
        return mId;
    }

    public LeadGenQualifier setFieldId(String value) {
        this.mId = value;
        return this;
    }

    public String getFieldLabel() {
        return mLabel;
    }

    public LeadGenQualifier setFieldLabel(String value) {
        this.mLabel = value;
        return this;
    }

    public String getFieldQuestion() {
        return mQuestion;
    }

    public LeadGenQualifier setFieldQuestion(String value) {
        this.mQuestion = value;
        return this;
    }


    synchronized /*package*/ static Gson getGson() {
        if (gson != null) {
            return gson;
        } else {
            gson = new GsonBuilder()
                    .excludeFieldsWithModifiers(Modifier.STATIC)
                    .excludeFieldsWithModifiers(Modifier.PROTECTED)
                    .disableHtmlEscaping()
                    .create();
        }
        return gson;
    }

    public LeadGenQualifier copyFrom(LeadGenQualifier instance) {
        this.mCategory = instance.mCategory;
        this.mFieldKey = instance.mFieldKey;
        this.mId = instance.mId;
        this.mLabel = instance.mLabel;
        this.mQuestion = instance.mQuestion;
        this.context = instance.context;
        this.rawValue = instance.rawValue;
        return this;
    }

    public static APIRequest.ResponseParser<LeadGenQualifier> getParser() {
        return new APIRequest.ResponseParser<LeadGenQualifier>() {
            public APINodeList<LeadGenQualifier> parseResponse(String response, APIContext context, APIRequest<LeadGenQualifier> request) throws MalformedResponseException {
                return LeadGenQualifier.parseResponse(response, context, request);
            }
        };
    }
}
