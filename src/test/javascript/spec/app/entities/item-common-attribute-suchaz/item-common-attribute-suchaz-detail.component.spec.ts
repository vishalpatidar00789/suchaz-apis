/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { ItemCommonAttributeSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/item-common-attribute-suchaz/item-common-attribute-suchaz-detail.component';
import { ItemCommonAttributeSuchazService } from '../../../../../../main/webapp/app/entities/item-common-attribute-suchaz/item-common-attribute-suchaz.service';
import { ItemCommonAttributeSuchaz } from '../../../../../../main/webapp/app/entities/item-common-attribute-suchaz/item-common-attribute-suchaz.model';

describe('Component Tests', () => {

    describe('ItemCommonAttributeSuchaz Management Detail Component', () => {
        let comp: ItemCommonAttributeSuchazDetailComponent;
        let fixture: ComponentFixture<ItemCommonAttributeSuchazDetailComponent>;
        let service: ItemCommonAttributeSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ItemCommonAttributeSuchazDetailComponent],
                providers: [
                    ItemCommonAttributeSuchazService
                ]
            })
            .overrideTemplate(ItemCommonAttributeSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ItemCommonAttributeSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemCommonAttributeSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new ItemCommonAttributeSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.itemCommonAttribute).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
