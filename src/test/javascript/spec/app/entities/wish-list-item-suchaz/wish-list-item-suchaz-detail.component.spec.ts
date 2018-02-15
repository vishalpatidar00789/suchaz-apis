/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { WishListItemSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/wish-list-item-suchaz/wish-list-item-suchaz-detail.component';
import { WishListItemSuchazService } from '../../../../../../main/webapp/app/entities/wish-list-item-suchaz/wish-list-item-suchaz.service';
import { WishListItemSuchaz } from '../../../../../../main/webapp/app/entities/wish-list-item-suchaz/wish-list-item-suchaz.model';

describe('Component Tests', () => {

    describe('WishListItemSuchaz Management Detail Component', () => {
        let comp: WishListItemSuchazDetailComponent;
        let fixture: ComponentFixture<WishListItemSuchazDetailComponent>;
        let service: WishListItemSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [WishListItemSuchazDetailComponent],
                providers: [
                    WishListItemSuchazService
                ]
            })
            .overrideTemplate(WishListItemSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WishListItemSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WishListItemSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new WishListItemSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.wishListItem).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
