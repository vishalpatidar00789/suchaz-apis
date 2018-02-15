/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { BuyLaterListItemSuchazComponent } from '../../../../../../main/webapp/app/entities/buy-later-list-item-suchaz/buy-later-list-item-suchaz.component';
import { BuyLaterListItemSuchazService } from '../../../../../../main/webapp/app/entities/buy-later-list-item-suchaz/buy-later-list-item-suchaz.service';
import { BuyLaterListItemSuchaz } from '../../../../../../main/webapp/app/entities/buy-later-list-item-suchaz/buy-later-list-item-suchaz.model';

describe('Component Tests', () => {

    describe('BuyLaterListItemSuchaz Management Component', () => {
        let comp: BuyLaterListItemSuchazComponent;
        let fixture: ComponentFixture<BuyLaterListItemSuchazComponent>;
        let service: BuyLaterListItemSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [BuyLaterListItemSuchazComponent],
                providers: [
                    BuyLaterListItemSuchazService
                ]
            })
            .overrideTemplate(BuyLaterListItemSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BuyLaterListItemSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BuyLaterListItemSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new BuyLaterListItemSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.buyLaterListItems[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
