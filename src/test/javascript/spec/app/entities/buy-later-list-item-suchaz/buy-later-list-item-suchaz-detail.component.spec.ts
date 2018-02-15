/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { BuyLaterListItemSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/buy-later-list-item-suchaz/buy-later-list-item-suchaz-detail.component';
import { BuyLaterListItemSuchazService } from '../../../../../../main/webapp/app/entities/buy-later-list-item-suchaz/buy-later-list-item-suchaz.service';
import { BuyLaterListItemSuchaz } from '../../../../../../main/webapp/app/entities/buy-later-list-item-suchaz/buy-later-list-item-suchaz.model';

describe('Component Tests', () => {

    describe('BuyLaterListItemSuchaz Management Detail Component', () => {
        let comp: BuyLaterListItemSuchazDetailComponent;
        let fixture: ComponentFixture<BuyLaterListItemSuchazDetailComponent>;
        let service: BuyLaterListItemSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [BuyLaterListItemSuchazDetailComponent],
                providers: [
                    BuyLaterListItemSuchazService
                ]
            })
            .overrideTemplate(BuyLaterListItemSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BuyLaterListItemSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BuyLaterListItemSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new BuyLaterListItemSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.buyLaterListItem).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
