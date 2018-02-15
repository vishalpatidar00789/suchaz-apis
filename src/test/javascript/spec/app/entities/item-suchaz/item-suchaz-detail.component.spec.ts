/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { ItemSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/item-suchaz/item-suchaz-detail.component';
import { ItemSuchazService } from '../../../../../../main/webapp/app/entities/item-suchaz/item-suchaz.service';
import { ItemSuchaz } from '../../../../../../main/webapp/app/entities/item-suchaz/item-suchaz.model';

describe('Component Tests', () => {

    describe('ItemSuchaz Management Detail Component', () => {
        let comp: ItemSuchazDetailComponent;
        let fixture: ComponentFixture<ItemSuchazDetailComponent>;
        let service: ItemSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ItemSuchazDetailComponent],
                providers: [
                    ItemSuchazService
                ]
            })
            .overrideTemplate(ItemSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ItemSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new ItemSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.item).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
