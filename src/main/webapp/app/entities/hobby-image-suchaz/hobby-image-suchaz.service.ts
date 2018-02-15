import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { HobbyImageSuchaz } from './hobby-image-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<HobbyImageSuchaz>;

@Injectable()
export class HobbyImageSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/hobby-images';

    constructor(private http: HttpClient) { }

    create(hobbyImage: HobbyImageSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(hobbyImage);
        return this.http.post<HobbyImageSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(hobbyImage: HobbyImageSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(hobbyImage);
        return this.http.put<HobbyImageSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<HobbyImageSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<HobbyImageSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<HobbyImageSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<HobbyImageSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: HobbyImageSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<HobbyImageSuchaz[]>): HttpResponse<HobbyImageSuchaz[]> {
        const jsonResponse: HobbyImageSuchaz[] = res.body;
        const body: HobbyImageSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to HobbyImageSuchaz.
     */
    private convertItemFromServer(hobbyImage: HobbyImageSuchaz): HobbyImageSuchaz {
        const copy: HobbyImageSuchaz = Object.assign({}, hobbyImage);
        return copy;
    }

    /**
     * Convert a HobbyImageSuchaz to a JSON which can be sent to the server.
     */
    private convert(hobbyImage: HobbyImageSuchaz): HobbyImageSuchaz {
        const copy: HobbyImageSuchaz = Object.assign({}, hobbyImage);
        return copy;
    }
}
