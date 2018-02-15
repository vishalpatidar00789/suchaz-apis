/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { WishListSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/wish-list-suchaz/wish-list-suchaz-detail.component';
import { WishListSuchazService } from '../../../../../../main/webapp/app/entities/wish-list-suchaz/wish-list-suchaz.service';
import { WishListSuchaz } from '../../../../../../main/webapp/app/entities/wish-list-suchaz/wish-list-suchaz.model';

describe('Component Tests', () => {

    describe('WishListSuchaz Management Detail Component', () => {
        let comp: WishListSuchazDetailComponent;
        let fixture: ComponentFixture<WishListSuchazDetailComponent>;
        let service: WishListSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [WishListSuchazDetailComponent],
                providers: [
                    WishListSuchazService
                ]
            })
            .overrideTemplate(WishListSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WishListSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WishListSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new WishListSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.wishList).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
