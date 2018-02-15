import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { StoreImageSuchaz } from './store-image-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<StoreImageSuchaz>;

@Injectable()
export class StoreImageSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/store-images';

    constructor(private http: HttpClient) { }

    create(storeImage: StoreImageSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(storeImage);
        return this.http.post<StoreImageSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(storeImage: StoreImageSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(storeImage);
        return this.http.put<StoreImageSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<StoreImageSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<StoreImageSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<StoreImageSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<StoreImageSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: StoreImageSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<StoreImageSuchaz[]>): HttpResponse<StoreImageSuchaz[]> {
        const jsonResponse: StoreImageSuchaz[] = res.body;
        const body: StoreImageSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to StoreImageSuchaz.
     */
    private convertItemFromServer(storeImage: StoreImageSuchaz): StoreImageSuchaz {
        const copy: StoreImageSuchaz = Object.assign({}, storeImage);
        return copy;
    }

    /**
     * Convert a StoreImageSuchaz to a JSON which can be sent to the server.
     */
    private convert(storeImage: StoreImageSuchaz): StoreImageSuchaz {
        const copy: StoreImageSuchaz = Object.assign({}, storeImage);
        return copy;
    }
}
